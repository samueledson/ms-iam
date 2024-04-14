package br.com.blendtecnologia.iam.infrastructure.persistence.entities;

import br.com.blendtecnologia.iam.core.domain.entities.Role;
import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.entities.UserStatus;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.blendtecnologia.iam.infrastructure.persistence.utils.IdConverter.convertId;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity(name = "users")
@Table(name = "users")
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public Set<RoleData> roles;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public static UserData from(User user) {
        UserData userData = new UserData();
        userData.setId(convertId(user.getId()));
        userData.setStatus(user.getStatus());
        userData.setCpf(user.getCpf());
        userData.setEmail(user.getEmail());
        userData.setPassword(user.getPassword());
        userData.setName(user.getName());
        userData.setCellphone(user.getCellphone());
        userData.setCreatedAt(user.getCreatedAt());
        userData.setUpdatedAt(user.getUpdatedAt());
        userData.setDeletedAt(user.getDeletedAt());
        if (user.getRoles() != null) {
            userData.setRoles(user.getRoles().stream().map(RoleData::from).collect(Collectors.toSet()));
        }
        return userData;
    }

    public User fromThis() {
        User user = new User();
        user.setId(new Identity(id));
        user.setStatus(status);
        user.setCpf(cpf);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setCellphone(cellphone);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);
        user.setDeletedAt(deletedAt);
        if (roles != null) {
            user.setRoles(roles.stream().map(RoleData::fromThis).collect(Collectors.toSet()));
        }
        return user;
    }

}
