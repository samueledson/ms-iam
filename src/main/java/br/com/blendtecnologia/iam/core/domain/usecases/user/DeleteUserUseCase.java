package br.com.blendtecnologia.iam.core.domain.usecases.user;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.repositories.UserRepository;
import br.com.blendtecnologia.iam.core.domain.usecases.UseCase;
import br.com.blendtecnologia.iam.core.domain.usecases.UseCaseExecutor;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCase implements UseCase<DeleteUserUseCase.InputValues, DeleteUserUseCase.OutputValues>{

    private final UseCaseExecutor useCaseExecutor;
    private final GetUserUseCase getUserUseCase;
    private final UserRepository userRepository;

    @Override
    public OutputValues execute(InputValues input) {

        final Identity id = input.id();

        CompletableFuture<User> user = useCaseExecutor.execute(
                getUserUseCase,
                new GetUserUseCase.InputValues(id),
                GetUserUseCase.OutputValues::user);

        userRepository.delete(user.join());
        return new OutputValues();
    }

    public record InputValues(Identity id) implements UseCase.InputValues {}

    public record OutputValues() implements UseCase.OutputValues {}

}
