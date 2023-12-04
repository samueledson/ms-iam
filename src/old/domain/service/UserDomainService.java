package br.com.blendtecnologia.msuser.domain.service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.blendtecnologia.msuser.domain.entity.User;
import br.com.blendtecnologia.msuser.domain.repository.UserRepository;

//@Service
//@Transactional
//@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            user.setDeletedAt(LocalDateTime.now(ZoneId.of("UTC")));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Transactional
    public User findByCpf(String cpf) {
        return userRepository.findByCpf(cpf).orElse(null);
    }

}
