package br.com.blendtecnologia.iam.infrastructure.persistence.repositories.jpa;

import br.com.blendtecnologia.iam.infrastructure.persistence.entities.RoleData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRoleRepository extends JpaRepository<RoleData, Long> {
        
    Optional<RoleData> findByName(String name);
    
}
