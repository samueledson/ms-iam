package br.com.blendtecnologia.iam.infrastructure.persistence.entities;

import static br.com.blendtecnologia.iam.infrastructure.persistence.utils.IdConverter.convertId;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.entities.UserStatus;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@ToString(of = {"status", "cpf", "email", "name", "cellphone", "createdAt", "updatedAt"})
@Entity(name = "user")
@Table(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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

    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime deletedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public static UserData from(User user) {
        return new UserData(
            convertId(user.getId()),
            user.getStatus(),
            user.getCpf(),
            user.getEmail(),
            user.getPassword(),
            user.getName(),
            user.getCellphone(),
            user.getCreatedAt(),
            user.getUpdatedAt(),
            user.getDeletedAt()
        );
    }

    public User fromThis() {
        return new User(
            new Identity(id),
            status,
            cpf,
            email,
            password,
            name,
            cellphone,
            createdAt,
            updatedAt,
            deletedAt
        );
    }

}
