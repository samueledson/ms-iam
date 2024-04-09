package br.com.blendtecnologia.iam.infrastructure.persistence.repositories;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.repositories.UserRepository;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
import br.com.blendtecnologia.iam.infrastructure.persistence.entities.UserData;
import br.com.blendtecnologia.iam.infrastructure.persistence.repositories.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
                .findById(id.number())
                .map(UserData::fromThis);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return jpaUserRepository
                .existsByCpf(cpf);
    }

    @Override
    public void delete(User user) {
        jpaUserRepository.delete(UserData.from(user));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository
                .findByEmail(username)
                .or(() -> jpaUserRepository.findByCpf(username))
                .map(UserData::fromThis);
    }

}
