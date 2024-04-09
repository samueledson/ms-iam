package br.com.blendtecnologia.iam.ui.rest.api.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.lang.Nullable;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UpdateUserRequest {

    @Nullable
    String cpf;

    @Nullable
    String email;

    @Nullable
    String password;

    @Nullable
    String name;

    @Nullable
    String cellphone;

}
