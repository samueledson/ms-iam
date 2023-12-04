package br.com.blendtecnologia.msuser.core.domain.entities;

import br.com.blendtecnologia.msuser.core.domain.valueobjects.Identity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Identity id;
    private UserStatus status;
    private String cpf;
    private String email;
    private String password;
    private String name;
    private String cellphone;    

    public static User newInstance(String cpf, String email, String password, String name, String cellphone) {
        return new User(
            Identity.nothing(),
            UserStatus.BLOCKED,
            cpf,
            email,
            password,
            name,
            cellphone
        );
    }
}
