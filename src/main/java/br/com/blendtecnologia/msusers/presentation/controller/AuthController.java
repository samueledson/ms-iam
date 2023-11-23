package br.com.blendtecnologia.msusers.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blendtecnologia.msusers.application.service.AuthService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("oauth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Map<String, String> tokens = authService.authenticate(username, password);
        if (tokens != null) {
            return ResponseEntity.ok(tokens);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // @PostMapping("/refresh-token")
    // public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> credentials) {
    //     String token = credentials.get("token");
    //     String refreshToken = authService.refreshToken(token);
    //     if (refreshToken != null) {
    //         return ResponseEntity.ok(refreshToken);
    //     } else {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    //     }
    // }

    @PostMapping("/revoke-token")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> credentials) {
        String token = credentials.get("token");
        authService.logout(token);
        return ResponseEntity.ok().build();
    }
    
}
