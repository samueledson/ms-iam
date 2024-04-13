package br.com.blendtecnologia.iam.infrastructure.persistence.repositories;

import br.com.blendtecnologia.iam.core.domain.entities.Role;
import br.com.blendtecnologia.iam.core.domain.repositories.RoleRepository;
import br.com.blendtecnologia.iam.infrastructure.persistence.entities.RoleData;
import br.com.blendtecnologia.iam.infrastructure.persistence.repositories.jpa.JpaRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return jpaRoleRepository
                .findByName(name)
                .map(RoleData::fromThis);
    }

}
