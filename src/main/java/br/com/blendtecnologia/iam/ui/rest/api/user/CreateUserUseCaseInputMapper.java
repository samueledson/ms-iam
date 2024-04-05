package br.com.blendtecnologia.iam.ui.rest.api.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.blendtecnologia.iam.core.domain.usecases.user.CreateUserUseCase;

@Service
public final class CreateUserUseCaseInputMapper {
    
    private PasswordEncoder passwordEncoder;

    public CreateUserUseCaseInputMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public CreateUserUseCase.InputValues map(UserRequest userRequest) {
        return new CreateUserUseCase.InputValues(
            userRequest.getCpf(),
            userRequest.getEmail(),
            passwordEncoder.encode(userRequest.getPassword()),
            userRequest.getName(),
            userRequest.getCellphone()
        );
    }

}
