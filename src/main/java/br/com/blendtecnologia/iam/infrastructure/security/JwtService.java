package br.com.blendtecnologia.iam.infrastructure.security;

import br.com.blendtecnologia.iam.infrastructure.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final ApplicationProperties applicationProperties;

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(applicationProperties.appSecurityJwtSecondsToExpire);

        String scopes = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer(applicationProperties.appName)
                .id("access_token")
                .issuedAt(now)
                .expiresAt(expiry)
                .subject(authentication.getName())
                .claim("scope", scopes)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
