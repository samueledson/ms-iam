package br.com.blendtecnologia.iam.ui.rest.api.user;

import br.com.blendtecnologia.iam.core.domain.entities.UserStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserRequest {

    @NotNull
    String cpf;

    @NotNull
    String email;

    @NotNull
    String password;

    @NotNull
    String name;

    @NotNull
    String cellphone;

    UserStatus status;

}
