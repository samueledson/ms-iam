package br.com.blendtecnologia.iam;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.TimeZone;

@SpringBootApplication
public class IamApplication {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }

    public static void main(String[] args) {
        SpringApplication.run(IamApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(PasswordEncoder passwordEncoder) {
        CharSequence rawPassword = "123456";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        //return args -> System.out.println(passwordEncoder.matches(rawPassword, encodedPassword));
        return args -> System.out.println(encodedPassword);
    }

}
