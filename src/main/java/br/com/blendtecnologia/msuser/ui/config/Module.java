package br.com.blendtecnologia.msuser.ui.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.blendtecnologia.msuser.core.domain.repositories.UserRepository;
import br.com.blendtecnologia.msuser.core.domain.usecases.user.CreateUserUseCase;

@Configuration
public class Module {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository repository) {
        return new CreateUserUseCase(repository);
    }

}
