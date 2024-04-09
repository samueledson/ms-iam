package br.com.blendtecnologia.iam.ui.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Module {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
