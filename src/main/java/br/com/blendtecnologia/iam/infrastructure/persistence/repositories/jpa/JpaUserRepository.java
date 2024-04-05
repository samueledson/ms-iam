package br.com.blendtecnologia.iam.infrastructure.persistence.repositories.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blendtecnologia.iam.infrastructure.persistence.entities.UserData;

public interface JpaUserRepository extends JpaRepository<UserData, Long> {
        
    boolean existsByCpf(String cpf);

    Optional<UserData> findByCpf(String cpf);
    
}
