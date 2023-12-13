package br.com.blendtecnologia.iam.core.domain.entities;

import com.github.javafaker.Faker;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.entities.UserStatus;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;

public final class TestCoreEntityGenerator {

    private static final Faker faker = new Faker();

    public static Identity randomId() {
        return new Identity(faker.number().randomNumber());
    }

    public static User randomUser() {
        return new User(
                randomId(),
                UserStatus.ACTIVE,
                faker.numerify("###########"),
                faker.internet().emailAddress(),
                faker.code().isbn10(),
                faker.name().name(),
                faker.numerify("###########"),
                null,
                null,
                null
        );
    }
    
}
