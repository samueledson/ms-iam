package br.com.blendtecnologia.iam.infrastructure.persistence.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.repositories.UserRepository;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
import br.com.blendtecnologia.iam.infrastructure.persistence.entities.UserData;
import br.com.blendtecnologia.iam.infrastructure.persistence.repositories.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    
    private final JpaUserRepository repository;

    @Override
    public User save(User user) {
        final UserData userData = UserData.from(user);
        return repository.save(userData).fromThis();
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Override
    public Optional<User> findById(Identity id) {
        return repository.findById(id.getNumber()).map(UserData::fromThis);
    }

    @Override
    public Optional<User> findByCpf(String cpf) {
        return repository.findByCpf(cpf).map(UserData::fromThis);
    }

}
