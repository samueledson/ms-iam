package br.com.blendtecnologia.iam.core.domain.repositories;

import java.util.List;
import java.util.Optional;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;

public interface UserRepository {

    List<User> getAll();
    
    User save(User user);

    boolean existsByCpf(String cpf);

    Optional<User> findById(Identity id);

    Optional<User> findByCpf(String cpf);

    void delete(User user);
}
