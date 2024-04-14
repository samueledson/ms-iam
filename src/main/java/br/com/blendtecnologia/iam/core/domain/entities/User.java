package br.com.blendtecnologia.iam.core.domain.entities;

import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

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
    private Set<Role> roles;

    public String getUsername() {
        return this.email != null ? this.email : this.cpf;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

}
