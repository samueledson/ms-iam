package br.com.blendtecnologia.iam.ui.rest.api.user;

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

}
