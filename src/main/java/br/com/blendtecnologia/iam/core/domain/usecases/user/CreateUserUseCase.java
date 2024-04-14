package br.com.blendtecnologia.iam.core.domain.usecases.user;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.entities.UserStatus;
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

        User user = new User();
        user.setStatus(input.status());
        user.setCpf(input.cpf());
        user.setEmail(input.email());
        user.setPassword(input.password());
        user.setName(input.name());
        user.setCellphone(input.cellphone());

        return new OutputValues(userRepository.save(user));
    }

    public record InputValues(String cpf, String email, String password, String name, String cellphone, UserStatus status)
            implements UseCase.InputValues {}

    public record OutputValues(User user) implements UseCase.OutputValues {}
}