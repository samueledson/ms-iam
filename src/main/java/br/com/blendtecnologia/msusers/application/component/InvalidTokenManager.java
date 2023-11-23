package br.com.blendtecnologia.msusers.application.component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InvalidTokenManager {

    @Value("${redis.key_prefix}")
    private String REDIS_KEY_PREFIX;

    @Value("${jwt.expiration_time}")
    private long JWT_EXPIRATION_TIME;

    private final RedisTemplate<String, String> redisTemplate;

    private String getInvalidTokenKey(String token) {
        return REDIS_KEY_PREFIX + "invalid-token:" + token;
    }

    public void add(String token) {
        String key = getInvalidTokenKey(token);
        redisTemplate.opsForValue().set(key, "", Duration.ofMillis(JWT_EXPIRATION_TIME));
    }

    public boolean isInvalid(String token){
        String key = getInvalidTokenKey(token);
        return redisTemplate.hasKey(key);
    }

}
