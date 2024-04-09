package br.com.blendtecnologia.iam.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

    @Value("${spring.application.name}")
    public String appName;

    @Value("${app.security.jwt.seconds-to-expire}")
    public long appSecurityJwtSecondsToExpire;

    @Value("${app.security.jwt.refresh.seconds-to-expire}")
    public long appSecurityJwtRefreshSecondsToExpire;

    @Value("${app.redis.key_prefix}")
    public String appRedisKeyPrefix;

    @Value("${app.security.jwt.public-key}")
    public RSAPublicKey appSecurityJwtPublicKey;

    @Value("${app.security.jwt.private-key}")
    public RSAPrivateKey appSecurityJwtPrivateKey;
    
}
