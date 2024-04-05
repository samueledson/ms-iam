package br.com.blendtecnologia.iam.ui.rest.api.user;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UpdateUserRequest {

    @Nullable
    private final String cpf;

    @Nullable
    private final String email;

    @Nullable
    private final String password;

    @Nullable
    private final String name;

    @Nullable
    private final String cellphone;

}
