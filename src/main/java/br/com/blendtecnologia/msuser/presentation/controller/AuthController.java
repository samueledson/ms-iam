package br.com.blendtecnologia.msuser.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blendtecnologia.msuser.application.service.AuthService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String REFRESH_TOKEN_KEY = "refresh_token";

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody Map<String, String> request) {
        String cpf = request.get("cpf");
        String password = request.get("password");

        Map<String, String> tokens = authService.authenticate(cpf, password);
        if (tokens != null) {
            Map<String, String> body = Map.of(
                    ACCESS_TOKEN_KEY, tokens.get("token"),
                    REFRESH_TOKEN_KEY, tokens.get("refreshToken"));
            return ResponseEntity.ok(body);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get(REFRESH_TOKEN_KEY);
        String token = authService.refreshToken(refreshToken);
        if (token != null) {
            Map<String, String> body = Map.of(
                    ACCESS_TOKEN_KEY, token);
            return ResponseEntity.ok(body);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/revoke-token")
    public ResponseEntity<Void> revoke(@RequestBody Map<String, String> request) {
        String token = request.get(ACCESS_TOKEN_KEY);
        boolean isRevoked = authService.revokeToken(token);
        if (isRevoked) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Teste ok (AuthController)");
    }

}
