package br.com.blendtecnologia.msuser;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"br.com.blendtecnologia.msuser.ui", "br.com.blendtecnologia.msuser.infrastructure"})
public class MsUsersApplication {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(MsUsersApplication.class, args);
    }

}
