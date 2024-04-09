package br.com.blendtecnologia.iam.core.domain.repositories;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    void delete(User user);

    boolean existsByCpf(String cpf);

    Optional<User> findById(Identity id);

    Optional<User> findByUsername(String username);

    List<User> getAll();

}
