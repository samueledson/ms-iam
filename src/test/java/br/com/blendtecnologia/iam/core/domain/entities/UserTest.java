package br.com.blendtecnologia.iam.core.domain.entities;

import org.junit.jupiter.api.Test;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void testNewInstance() throws InterruptedException {

        // given
        User user = TestCoreEntityGenerator.randomUser();

        // when
        User newUser = User.newInstance(
            user.getCpf(),
            user.getEmail(),
            user.getPassword(),
            user.getName(),
            user.getCellphone()
        );

        // then
        assertThat(newUser.getId()).isEqualTo(Identity.nothing());
        assertThat(newUser.getStatus()).isEqualTo(user.getStatus());
        assertThat(newUser.getCpf()).isEqualTo(user.getCpf());
        assertThat(newUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(newUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(newUser.getName()).isEqualTo(user.getName());
        assertThat(newUser.getCellphone()).isEqualTo(user.getCellphone());

    }
}