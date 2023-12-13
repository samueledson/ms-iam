package br.com.blendtecnologia.iam.infrastructure;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.github.javafaker.Faker;

import br.com.blendtecnologia.iam.infrastructure.ApplicationProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

//@SpringBootTest
class ApplicationPropertiesTest {

    @Autowired
    private Environment environment;

    //private static final Faker faker = new Faker();
    @Autowired
    private ApplicationProperties applicationProperties;

    // @Value("${spring.security.user.name}")
    // public String springSecurityUserName;

    // private static final String SPRING_SECURITY_USER_NAME = faker.name().username();
    // private static final String SPRING_SECURITY_USER_PASSWORD = faker.internet().password();
    // private static final String APP_NAME = faker.name().name();
    // private static final String APP_JWT_SECRET_KEY = faker.internet().password();
    // private static final long APP_JWT_EXPIRATION_TIME = faker.number().randomNumber();
    // private static final long APP_JWT_REFRESH_EXPIRATION_TIME = faker.number().randomNumber();
    // private static final String APP_REDIS_KEY_PREFIX = faker.name().username();

    // @BeforeEach
    // public void setup() {
    //     MockitoAnnotations.openMocks(this);
    //     applicationProperties = new ApplicationProperties();
    //     //applicationProperties = new ApplicationProperties();
    //     // applicationProperties.springSecurityUserName = SPRING_SECURITY_USER_NAME;
    //     // applicationProperties.springSecurityUserPassword = SPRING_SECURITY_USER_PASSWORD;
    //     // applicationProperties.appName = APP_NAME;
    //     // applicationProperties.appJwtSecretKey = APP_JWT_SECRET_KEY;
    //     // applicationProperties.appJwtExpirationTime = APP_JWT_EXPIRATION_TIME;
    //     // applicationProperties.appJwtRefreshExpirationTime = APP_JWT_REFRESH_EXPIRATION_TIME;
    //     // applicationProperties.appRedisKeyPrefix = APP_REDIS_KEY_PREFIX;
    // }

    //@Test
    void testSpringSecurityUserName() {
        var teste = environment.getProperty("spring.security.user.name");
        assertEquals(environment.getProperty("spring.security.user.name"), applicationProperties.springSecurityUserName);
    }

    // @Test
    // void testSpringSecurityUserPassword() {
    //     assertEquals(SPRING_SECURITY_USER_PASSWORD, applicationProperties.springSecurityUserPassword);
    // }

    // @Test
    // void testAppName() {
    //     assertEquals(APP_NAME, applicationProperties.appName);
    // }

    // @Test
    // void testAppJwtSecretKey() {
    //     assertEquals(APP_JWT_SECRET_KEY, applicationProperties.appJwtSecretKey);
    // }

    // @Test
    // void testAppJwtExpirationTime() {
    //     assertEquals(APP_JWT_EXPIRATION_TIME, applicationProperties.appJwtExpirationTime);
    // }

    // @Test
    // void testAppJwtRefreshExpirationTime() {
    //     assertEquals(APP_JWT_REFRESH_EXPIRATION_TIME, applicationProperties.appJwtRefreshExpirationTime);
    // }

    // @Test
    // void testAppRedisKeyPrefix() {
    //     assertEquals(APP_REDIS_KEY_PREFIX, applicationProperties.appRedisKeyPrefix);
    // }

}