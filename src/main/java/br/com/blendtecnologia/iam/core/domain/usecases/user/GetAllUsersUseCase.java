package br.com.blendtecnologia.iam.core.domain.usecases.user;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.repositories.UserRepository;
import br.com.blendtecnologia.iam.core.domain.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUsersUseCase implements UseCase<GetAllUsersUseCase.InputValues, GetAllUsersUseCase.OutputValues> {

    private final UserRepository userRepository;

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(userRepository.getAll());
    }

    public record InputValues() implements UseCase.InputValues {}

    public record OutputValues(List<User> users) implements UseCase.OutputValues {}
    
}
