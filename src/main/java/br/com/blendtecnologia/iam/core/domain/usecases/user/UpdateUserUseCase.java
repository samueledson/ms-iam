package br.com.blendtecnologia.iam.core.domain.usecases.user;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import br.com.blendtecnologia.iam.core.domain.usecases.UseCaseExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.repositories.UserRepository;
import br.com.blendtecnologia.iam.core.domain.usecases.UseCase;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase implements UseCase<UpdateUserUseCase.InputValues, UpdateUserUseCase.OutputValues> {

    private final GetUserUseCase getUserUseCase;
    private final PasswordEncoder passwordEncoder;
    private final UseCaseExecutor useCaseExecutor;
    private final UserRepository userRepository;

    @Override
    public OutputValues execute(InputValues input) {

        final Identity id = input.getId();

        CompletableFuture<User> userCompletableFuture = useCaseExecutor.execute(
                getUserUseCase,
                new GetUserUseCase.InputValues(id),
                GetUserUseCase.OutputValues::user);

        User user = userCompletableFuture.join();

        input.getCpf().ifPresent(user::setCpf);
        input.getEmail().ifPresent(user::setEmail);
        input.getName().ifPresent(user::setName);
        input.getCellphone().ifPresent(user::setCellphone);

        input.getPassword().ifPresent(password ->
                user.setPassword(passwordEncoder.encode(password))
        );

        return new OutputValues(userRepository.save(user));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final Identity id;
        private final Optional<String> cpf;
        private final Optional<String> email;
        private final Optional<String> password;
        private final Optional<String> name;
        private final Optional<String> cellphone;
    }

    public record OutputValues(User user) implements UseCase.OutputValues {}
    
}
