package br.com.blendtecnologia.iam.infrastructure.persistence.repositories;

import java.util.List;
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

    private final JpaUserRepository jpaUserRepository;

    @Override
    public List<User> getAll() {
        return jpaUserRepository
                .findAll()
                .stream()
                .map(UserData::fromThis)
                .toList();
    }

    @Override
    public User save(User user) {
        final UserData userData = UserData.from(user);
        return jpaUserRepository
                .save(userData)
                .fromThis();
    }

    @Override
    public Optional<User> findById(Identity id) {
        return jpaUserRepository
                .findById(id.getNumber())
                .map(UserData::fromThis);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return jpaUserRepository
                .existsByCpf(cpf);
    }

    @Override
    public Optional<User> findByCpf(String cpf) {
        return jpaUserRepository
                .findByCpf(cpf)
                .map(UserData::fromThis);
    }

    @Override
    public void delete(User user) {
        jpaUserRepository.delete(UserData.from(user));
    }

}
