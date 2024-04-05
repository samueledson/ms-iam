package br.com.blendtecnologia.iam.infrastructure.persistence.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.com.blendtecnologia.iam.core.domain.entities.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
import br.com.blendtecnologia.iam.infrastructure.persistence.entities.UserData;
import br.com.blendtecnologia.iam.infrastructure.persistence.repositories.jpa.JpaUserRepository;

class UserRepositoryImplTest {

    @Mock
    private JpaUserRepository jpaUserRepository;

    @InjectMocks
    private UserRepositoryImpl userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    public User getUser() {
        Identity id = new Identity(1L);
        return new User(
                id,
                UserStatus.ACTIVE,
                "validCPF",
                "validEmail",
                "validPassword",
                "validName",
                "validCellphone",
                null,
                null,
                null
        );
    }

    @Test
    @DisplayName("save returns saved user")
    void saveReturnsSavedUser() {
        User user = this.getUser();
        UserData userData = UserData.from(user);
        when(jpaUserRepository.save(userData)).thenReturn(userData);

        User result = userRepository.save(user);

        assertEquals(user, result);
    }

    @Test
    @DisplayName("findById returns user when user is present")
    void findByIdReturnsUserWhenUserIsPresent() {
        Identity id = Identity.nothing();
        UserData userData = new UserData();
        when(jpaUserRepository.findById(id.getNumber())).thenReturn(Optional.of(userData));

        Optional<User> result = userRepository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(userData.fromThis(), result.get());
    }

    @Test
    @DisplayName("findById returns empty when no user is present")
    void findByIdReturnsEmptyWhenNoUserIsPresent() {
        Identity id = Identity.nothing();
        when(jpaUserRepository.findById(id.getNumber())).thenReturn(Optional.empty());

        Optional<User> result = userRepository.findById(id);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("getAll returns list of users when users are present")
    void getAllReturnsListOfUsersWhenUsersArePresent() {
        UserData userData = new UserData();
        when(jpaUserRepository.findAll()).thenReturn(List.of(userData));

        List<User> result = userRepository.getAll();

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("getAll returns empty list when no users are present")
    void getAllReturnsEmptyListWhenNoUsersArePresent() {
        when(jpaUserRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> result = userRepository.getAll();

        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("existsByCpf returns true when user with given CPF exists")
    void existsByCpfReturnsTrueWhenUserWithGivenCpfExists() {
        when(jpaUserRepository.existsByCpf("validCPF")).thenReturn(true);

        boolean result = userRepository.existsByCpf("validCPF");

        assertTrue(result);
    }

    @Test
    @DisplayName("existsByCpf returns false when user with given CPF does not exist")
    void existsByCpfReturnsFalseWhenUserWithGivenCpfDoesNotExist() {
        when(jpaUserRepository.existsByCpf("invalidCPF")).thenReturn(false);

        boolean result = userRepository.existsByCpf("invalidCPF");

        assertFalse(result);
    }

    @Test
    @DisplayName("findByCpf returns user when user is present")
    void findByCpfReturnsUserWhenUserIsPresent() {
        UserData userData = new UserData();
        when(jpaUserRepository.findByCpf("validCPF")).thenReturn(Optional.of(userData));

        Optional<UserData> result = jpaUserRepository.findByCpf("validCPF");

        assertTrue(result.isPresent());
        assertEquals(userData, result.get());
    }

    @Test
    @DisplayName("findByCpf returns empty when no user is present")
    void findByCpfReturnsEmptyWhenNoUserIsPresent() {
        when(jpaUserRepository.findByCpf("invalidCPF")).thenReturn(Optional.empty());

        Optional<UserData> result = jpaUserRepository.findByCpf("invalidCPF");

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("delete removes user")
    void deleteRemovesUser() {
        User user = this.getUser();
        userRepository.delete(user);

        verify(jpaUserRepository, times(1)).delete(UserData.from(user));
    }
}