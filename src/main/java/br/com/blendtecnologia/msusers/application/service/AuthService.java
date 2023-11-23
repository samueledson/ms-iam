package br.com.blendtecnologia.msusers.application.service;

import br.com.blendtecnologia.msusers.application.component.InvalidTokenManager;
import br.com.blendtecnologia.msusers.application.component.JwtTokenProvider;
import br.com.blendtecnologia.msusers.presentation.dto.UserDTO;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final InvalidTokenManager invalidTokenManager;

    public Map<String, String> authenticate(String username, String password) {
        UserDTO userDTO = userService.findByUsername(username);
        if (userDTO != null && passwordEncoder.matches(password, userDTO.getPassword())) {
            String token = jwtTokenProvider.generateToken(userDTO.getId(), userDTO.getUsername());
            String refreshToken = jwtTokenProvider.generateRefreshToken();
            return Map.of("token", token, "refreshToken", refreshToken);
        }
        return null;
    }

    public void logout(String token) {
        invalidTokenManager.add(token);
    }
    
}