package br.com.blendtecnologia.iam.ui.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.blendtecnologia.iam.core.domain.repositories.UserRepository;
import br.com.blendtecnologia.iam.core.domain.usecases.user.CreateUserUseCase;
import br.com.blendtecnologia.iam.core.domain.usecases.user.GetUserUseCase;

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

    @Bean
    public GetUserUseCase getUserUseCase(UserRepository repository) {
        return new GetUserUseCase(repository);
    }

}
