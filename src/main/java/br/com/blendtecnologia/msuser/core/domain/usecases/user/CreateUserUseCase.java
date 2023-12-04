package br.com.blendtecnologia.msuser.core.domain.usecases.user;

import br.com.blendtecnologia.msuser.core.domain.entities.User;
import br.com.blendtecnologia.msuser.core.domain.exceptions.CpfAlreadyUsedException;
import br.com.blendtecnologia.msuser.core.domain.repositories.UserRepository;
import br.com.blendtecnologia.msuser.core.domain.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class CreateUserUseCase implements UseCase<CreateUserUseCase.InputValues, CreateUserUseCase.OutputValues>{
    
    private final UserRepository userRepository;
    
    @Override
    public OutputValues execute(InputValues input) {
        if(userRepository.existsByCpf(input.getCpf())) {
            throw new CpfAlreadyUsedException("CPF already used");
        }

        User user = User.newInstance(
            input.getCpf(),
            input.getEmail(),
            input.getPassword(),
            input.getName(),
            input.getCellphone()
        );

        return new OutputValues(userRepository.save(user));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final String cpf;
        private final String email;
        private final String password;
        private final String name;
        private final String cellphone;
    }
    
    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final User user;
    }
}