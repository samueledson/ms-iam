package br.com.blendtecnologia.iam.core.domain.usecases.user;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.exceptions.NotFoundException;
import br.com.blendtecnologia.iam.core.domain.repositories.UserRepository;
import br.com.blendtecnologia.iam.core.domain.usecases.UseCase;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class GetUserUseCase implements UseCase<GetUserUseCase.InputValues, GetUserUseCase.OutputValues>{

    private final UserRepository userRepository;

    @Override
    public OutputValues execute(InputValues input) {
        final Identity id = input.getId();

        return userRepository.findById(id)
            .map(OutputValues::new)
            .orElseThrow(() -> new NotFoundException("User %s not found", id.getNumber()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final Identity id;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final User user;
    }
    
}
