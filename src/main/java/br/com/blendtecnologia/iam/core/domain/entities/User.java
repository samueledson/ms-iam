package br.com.blendtecnologia.iam.core.domain.entities;

import java.time.LocalDateTime;

import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public static User newInstance(String cpf, String email, String password, String name, String cellphone) {
        return new User(
            Identity.nothing(),
            UserStatus.ACTIVE,
            cpf,
            email,
            password,
            name,
            cellphone,
            null,
            null,
            null
        );
    }
}
