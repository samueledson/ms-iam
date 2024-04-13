package br.com.blendtecnologia.iam.infrastructure.persistence.entities;

import br.com.blendtecnologia.iam.core.domain.entities.Role;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static br.com.blendtecnologia.iam.infrastructure.persistence.utils.IdConverter.convertId;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity(name = "roles")
@Table(name = "roles")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

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

    public static RoleData from(Role role) {
        RoleData roleData = new RoleData();
        roleData.setId(convertId(role.getId()));
        roleData.setName(role.getName());
        roleData.setDescription(role.getDescription());
        roleData.setCreatedAt(role.getCreatedAt());
        roleData.setUpdatedAt(role.getUpdatedAt());
        roleData.setDeletedAt(role.getDeletedAt());
        return roleData;
    }

    public Role fromThis() {
        Role role = new Role();
        role.setId(new Identity(id));
        role.setName(name);
        role.setCreatedAt(createdAt);
        role.setUpdatedAt(updatedAt);
        role.setDeletedAt(deletedAt);
        return role;
    }

}
