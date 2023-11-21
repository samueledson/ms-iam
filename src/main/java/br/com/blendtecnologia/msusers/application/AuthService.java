package br.com.blendtecnologia.msusers.application;

import br.com.blendtecnologia.msusers.presentation.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public String authenticate(String username, String password) {
        UserDTO userDTO = userService.findByUsername(username);
        if (userDTO != null && passwordEncoder.matches(password, userDTO.getPassword())) {
            // Gere o token JWT
            String token = generateJwtToken(userDTO.getId(), userDTO.getUsername());
            return token;
        }
        return null;
    }

    public Key secretKey(){
        //return Keys.secretKeyFor(SignatureAlgorithm.HS512);
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private String generateJwtToken(Long userId, String username) {
        LocalDateTime currentTime = LocalDateTime.now();

        // Defina uma data de expiração para o token (por exemplo, 30 minutos a partir do momento atual)
        LocalDateTime expirationTime = currentTime.plusMinutes(30);

        // Converte as datas para o formato apropriado do Java Date
        Date currentDateTime = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
        Date expirationDateTime = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());

        // Cria os claims (dados) do token
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("userId", userId);

        // Gera o token JWT
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(currentDateTime)
                .setExpiration(expirationDateTime)
                .signWith(secretKey())
                //.signWith(SignatureAlgorithm.HS512, "sua-chave-secreta") // Substitua "sua-chave-secreta" pela sua chave secreta real
                .compact();

        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String refreshToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey()).build().parseClaimsJws(token).getBody();
        Long userId = claims.get("userId", Long.class);
        String username = claims.getSubject();
        String renewedToken = generateJwtToken(userId, username);
        return renewedToken;
    }
    
}