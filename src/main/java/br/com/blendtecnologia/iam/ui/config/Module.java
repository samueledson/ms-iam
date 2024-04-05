package br.com.blendtecnologia.iam.ui.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.blendtecnologia.iam.core.domain.repositories.UserRepository;
import br.com.blendtecnologia.iam.core.domain.usecases.user.CreateUserUseCase;
import br.com.blendtecnologia.iam.core.domain.usecases.user.GetAllUsersUseCase;
import br.com.blendtecnologia.iam.core.domain.usecases.user.GetUserUseCase;
import br.com.blendtecnologia.iam.core.domain.usecases.user.UpdateUserUseCase;

@Configuration
public class Module {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
