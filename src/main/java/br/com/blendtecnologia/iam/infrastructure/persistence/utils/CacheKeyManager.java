package br.com.blendtecnologia.iam.infrastructure.persistence.utils;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import br.com.blendtecnologia.iam.infrastructure.ApplicationProperties;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CacheKeyManager {

    private final RedisTemplate<String, String> redisTemplate;
    private final ApplicationProperties applicationProperties;

    private String generateKey(String key) {
        return applicationProperties.appRedisKeyPrefix + key;
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(generateKey(key));
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(generateKey(key), value);
    }

    public void set(String key, String value, Duration timeout) {
        redisTemplate.opsForValue().set(generateKey(key), value, timeout);
    }

    public void delete(String key) {
        redisTemplate.delete(generateKey(key));
    }

    public boolean hasKey(String key) {
        return redisTemplate != null && redisTemplate.hasKey(generateKey(key));
    }
    
}
