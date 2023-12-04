package br.com.blendtecnologia.msuser.infrastructure.persistence.entities;

import static br.com.blendtecnologia.msuser.infrastructure.persistence.utils.IdConverter.convertId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.blendtecnologia.msuser.core.domain.entities.User;
import br.com.blendtecnologia.msuser.core.domain.entities.UserStatus;
import br.com.blendtecnologia.msuser.core.domain.valueobjects.Identity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = {"status", "cpf", "email", "name", "cellphone"})
@Entity(name = "user")
@Table(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_id", nullable = false, columnDefinition = "BIGINT UNSIGNED DEFAULT 1")
    private UserStatus status;

    @Column(nullable = false, unique = true, length = 11, updatable = false)
    private String cpf;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 11)
    private String cellphone;

    // TODO: test
    public static UserData from(User user) {
        return new UserData(
            convertId(user.getId()),
            user.getStatus(),
            user.getCpf(),
            user.getEmail(),
            user.getPassword(),
            user.getName(),
            user.getCellphone()
        );
    }

    // TODO: test
    public User fromThis() {
        return new User(
            new Identity(id),
            status,
            cpf,
            email,
            password,
            name,
            cellphone
        );
    }

}
