package br.com.blendtecnologia.msuser.infrastructure.security;

import java.time.Duration;
import org.springframework.stereotype.Component;

import br.com.blendtecnologia.msuser.infrastructure.ApplicationProperties;
import br.com.blendtecnologia.msuser.infrastructure.persistence.utils.CacheKeyManager;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenRevocationManager {

    private final ApplicationProperties applicationProperties;
    private final CacheKeyManager cacheKeyManager;

    private String generateCacheKey(String token) {
        return "invalid-token:" + token;
    }

    public void revoke(String token) {
        cacheKeyManager.set(generateCacheKey(token), "", Duration.ofMillis(applicationProperties.appJwtExpirationTime));
    }

    public boolean isRevoked(String token){
        return cacheKeyManager.hasKey(generateCacheKey(token));
    }

}
