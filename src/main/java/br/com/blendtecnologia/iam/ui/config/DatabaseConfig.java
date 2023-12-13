package br.com.blendtecnologia.iam.ui.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "br.com.blendtecnologia.iam.infrastructure.persistence.entities")
@EnableJpaRepositories(basePackages = "br.com.blendtecnologia.iam.infrastructure.persistence.repositories")
public class DatabaseConfig {
    
}
