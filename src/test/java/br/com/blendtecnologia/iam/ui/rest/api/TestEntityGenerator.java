package br.com.blendtecnologia.iam.ui.rest.api;

import com.github.javafaker.Faker;

import br.com.blendtecnologia.iam.ui.rest.api.user.UserRequest;

public final class TestEntityGenerator {

    private static final Faker faker = new Faker();

    public static String randomCpf() {
        return faker.numerify("###########");
    }

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    public static String randomPassword() {
        return faker.code().isbn10();
    }

    public static String randomName() {
        return faker.name().name();
    }   

    public static String randomCellphone() {
        return faker.numerify("###########");
    }

    public static UserRequest randomUserRequest() {
        return new UserRequest(
            randomCpf(),
            randomEmail(),
            randomPassword(),
            randomName(),
            randomCellphone()
        );
    }
    
}
