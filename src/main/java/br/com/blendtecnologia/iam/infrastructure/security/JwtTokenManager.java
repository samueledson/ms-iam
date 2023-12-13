package br.com.blendtecnologia.iam.infrastructure.security;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.infrastructure.ApplicationProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenManager {

    private final JwtTokenRevocationManager jwtTokenRevocationManager;
    private final ApplicationProperties applicationProperties;

    private Key secretKey(){
        return Keys.hmacShaKeyFor(applicationProperties.appJwtSecretKey.getBytes());
    }

    private Date getCurrentDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        return Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getExpirationDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationTime = currentTime.plusSeconds(applicationProperties.appJwtExpirationTime / 1000);
        return Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getCpf());
        claims.put("user_id", user.getId());
        claims.put("user_email", user.getEmail());
        return Jwts.builder()
                .setIssuer(applicationProperties.appName)
                .setId("access_token")
                .setClaims(claims)
                .setIssuedAt(getCurrentDate())
                .setExpiration(getExpirationDate())
                .signWith(secretKey())
                .compact();

    }

    public String generateRefreshToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getCpf());
        claims.put("user_id", user.getId());
        return Jwts.builder()
                .setIssuer(applicationProperties.appName)
                .setId("refresh_token")
                .setClaims(claims)
                .setIssuedAt(getCurrentDate())
                .setExpiration(getExpirationDate())
                .signWith(secretKey())
                .compact();

    }

    public boolean validateToken(String token) {
        try {
            if (jwtTokenRevocationManager.isRevoked(token)) {
                return false;
            }
            Jwts.parserBuilder().setSigningKey(secretKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
}
