package br.com.blendtecnologia.iam;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"br.com.blendtecnologia.iam.ui", "br.com.blendtecnologia.iam.infrastructure"})
public class IamApplication {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }

    public static void main(String[] args) {
        SpringApplication.run(IamApplication.class, args);
    }

}
