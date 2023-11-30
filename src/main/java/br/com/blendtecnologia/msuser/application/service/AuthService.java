package br.com.blendtecnologia.msuser.application.service;

import io.jsonwebtoken.Claims;

import java.util.Collections;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.blendtecnologia.msuser.application.component.JwtTokenManager;
import br.com.blendtecnologia.msuser.application.component.JwtTokenRevocationManager;
import br.com.blendtecnologia.msuser.domain.entity.User;
import br.com.blendtecnologia.msuser.domain.service.UserDomainService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDomainService userDomainService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenManager jwtTokenManager;
    private final JwtTokenRevocationManager jwtTokenRevocationManager;

    public Map<String, String> authenticate(String cpf, String password) {
        User user = userDomainService.findByCpf(cpf);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtTokenManager.generateToken(user);
            String refreshToken = jwtTokenManager.generateRefreshToken(user);
            return Map.of("token", token, "refreshToken", refreshToken);
        }
        return Collections.emptyMap();
    }

    public String refreshToken(String refreshToken) {
        if(jwtTokenManager.validateToken(refreshToken)) {
            Claims claims = jwtTokenManager.getClaimsFromToken(refreshToken);
            if (claims != null) {
                String cpf = claims.getSubject();
                User user = userDomainService.findByCpf(cpf);
                if (user != null) {
                    return jwtTokenManager.generateRefreshToken(user);
                }
            }
        }
        return null;
    }

    public boolean revokeToken(String token) {
        if(jwtTokenManager.validateToken(token)) {
            jwtTokenRevocationManager.revoke(token);
            return true;
        }
        return false;
    }
    
}