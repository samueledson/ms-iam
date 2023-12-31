package br.com.blendtecnologia.iam.ui.rest.api.user;

import br.com.blendtecnologia.iam.core.domain.usecases.user.CreateUserUseCase;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserRequest {

    @NotNull
    private final String cpf;

    @NotNull
    private final String email;

    @NotNull
    private final String password;

    @NotNull
    private final String name;

    @NotNull
    private final String cellphone;

    public static CreateUserUseCase.InputValues from(UserRequest userRequest) {
        return new CreateUserUseCase.InputValues(
                userRequest.getCpf(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getName(),
                userRequest.getCellphone());
    }

}
