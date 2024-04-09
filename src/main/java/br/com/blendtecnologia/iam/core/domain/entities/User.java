package br.com.blendtecnologia.iam.core.domain.entities;

import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    public String getUsername() {
        return this.email != null ? this.email : this.cpf;
    }

    public static User newInstance(String cpf, String email, String password, String name, String cellphone) {
        User user = new User();
        user.setCpf(cpf);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setCellphone(cellphone);
        return user;
    }

}
