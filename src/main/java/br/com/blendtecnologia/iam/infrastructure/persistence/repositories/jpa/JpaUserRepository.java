package br.com.blendtecnologia.iam.infrastructure.persistence.repositories.jpa;

import br.com.blendtecnologia.iam.infrastructure.persistence.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserData, Long> {
        
    boolean existsByCpf(String cpf);

    Optional<UserData> findByCpf(String cpf);

    Optional<UserData> findByEmail(String email);
    
}
