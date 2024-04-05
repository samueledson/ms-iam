package br.com.blendtecnologia.iam.core.domain.usecases.user;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.exceptions.CpfAlreadyUsedException;
import br.com.blendtecnologia.iam.core.domain.repositories.UserRepository;
import br.com.blendtecnologia.iam.core.domain.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase implements UseCase<CreateUserUseCase.InputValues, CreateUserUseCase.OutputValues>{
    
    private final UserRepository userRepository;
    
    @Override
    public OutputValues execute(InputValues input) {
        if(userRepository.existsByCpf(input.cpf())) {
            throw new CpfAlreadyUsedException("CPF already used");
        }

        User user = User.newInstance(
            input.cpf(),
            input.email(),
            input.password(),
            input.name(),
            input.cellphone()
        );

        return new OutputValues(userRepository.save(user));
    }

    public record InputValues(String cpf, String email, String password, String name, String cellphone) implements UseCase.InputValues {}

    public record OutputValues(User user) implements UseCase.OutputValues {}
}