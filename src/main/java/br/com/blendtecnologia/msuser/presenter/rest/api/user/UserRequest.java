package br.com.blendtecnologia.msuser.presenter.rest.api.user;

import br.com.blendtecnologia.msuser.core.domain.usecases.user.CreateUserUseCase;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
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

    public static CreateUserUseCase.InputValues from (UserRequest userRequest) {
        return new CreateUserUseCase.InputValues(
            userRequest.getCpf(),
            userRequest.getEmail(),
            userRequest.getPassword(),
            userRequest.getName(),
            userRequest.getCellphone()
        );
    }

}
