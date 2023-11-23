package br.com.blendtecnologia.msusers.application.component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret_key}")
    public String JWT_SECRET_KEY;

    @Value("${jwt.expiration_time}")
    private long JWT_EXPIRATION_TIME;

    @Value("${jwt.refresh_expiration_time}")
    private long JWT_REFRESH_EXPIRATION_TIME;

    private final InvalidTokenManager invalidTokenManager;

    private Key secretKey(){
        return Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes());
    }

    public String generateToken(Long userId, String username) {
        // LocalDateTime currentTime = LocalDateTime.now();

        // LocalDateTime expirationTime = currentTime.plusMinutes(minutesToExpire);

        // Date currentDateTime = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
        // Date expirationDateTime = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());

        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + JWT_EXPIRATION_TIME);

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("userId", userId);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(secretKey())
                .compact();

        return token;
    }

    public String generateRefreshToken() {
        LocalDateTime currentTime = LocalDateTime.now();

        LocalDateTime expirationTime = currentTime.plusSeconds(JWT_REFRESH_EXPIRATION_TIME / 1000);

        Date currentDateTime = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
        Date expirationDateTime = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());

        // Date currentDate = new Date();
        // Date expirationDate = new Date(currentDate.getTime() + expirationTime);

        String token = Jwts.builder()
                .setIssuedAt(currentDateTime)
                .setExpiration(expirationDateTime)
                .signWith(secretKey())
                .compact();

        return token;
    }

    public boolean validateToken(String token) {
        try {
            if (invalidTokenManager.isInvalid(token)) {
                return false;
            }
            Jwts.parserBuilder().setSigningKey(secretKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getSubjectFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
    
}
