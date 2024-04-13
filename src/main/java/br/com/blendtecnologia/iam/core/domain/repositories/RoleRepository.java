package br.com.blendtecnologia.iam.core.domain.repositories;

import br.com.blendtecnologia.iam.core.domain.entities.Role;

import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findByName(String name);

}
