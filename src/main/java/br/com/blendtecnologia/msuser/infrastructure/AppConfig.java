package br.com.blendtecnologia.msuser.infrastructure;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final AccessTokenFilter accessTokenFilter;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public FilterRegistrationBean<AccessTokenFilter> accessTokenFilterRegistration(){
        FilterRegistrationBean<AccessTokenFilter> registrationBean = new FilterRegistrationBean<>();
            
        registrationBean.setFilter(accessTokenFilter);
        registrationBean.addUrlPatterns("/auth/teste/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
            
        return registrationBean;    
    }
}
