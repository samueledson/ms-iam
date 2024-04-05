package br.com.blendtecnologia.iam.core.domain.usecases.user;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.repositories.UserRepository;
import br.com.blendtecnologia.iam.core.domain.usecases.UseCase;
import br.com.blendtecnologia.iam.core.domain.usecases.UseCaseExecutor;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase implements UseCase<UpdateUserUseCase.InputValues, UpdateUserUseCase.OutputValues> {

    private final GetUserUseCase getUserUseCase;
    private final PasswordEncoder passwordEncoder;
    private final UseCaseExecutor useCaseExecutor;
    private final UserRepository userRepository;

    @Override
    public OutputValues execute(InputValues input) {

        final Identity id = input.id();

        CompletableFuture<User> userCompletableFuture = useCaseExecutor.execute(
                getUserUseCase,
                new GetUserUseCase.InputValues(id),
                GetUserUseCase.OutputValues::user);

        User user = userCompletableFuture.join();

        input.cpf().ifPresent(user::setCpf);
        input.email().ifPresent(user::setEmail);
        input.name().ifPresent(user::setName);
        input.cellphone().ifPresent(user::setCellphone);

        input.password().ifPresent(password ->
                user.setPassword(passwordEncoder.encode(password))
        );

        return new OutputValues(userRepository.save(user));
    }

    public record InputValues(Identity id, Optional<String> cpf, Optional<String> email, Optional<String> password,
                              Optional<String> name, Optional<String> cellphone) implements UseCase.InputValues {}

    public record OutputValues(User user) implements UseCase.OutputValues {}

}
