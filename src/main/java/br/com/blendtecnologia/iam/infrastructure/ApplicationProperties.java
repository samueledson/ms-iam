package br.com.blendtecnologia.iam.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

    @Value("${spring.security.user.name}")
    public String springSecurityUserName;

    @Value("${spring.security.user.password}")
    public String springSecurityUserPassword;

    @Value("${app.name}")
    public String appName;

    @Value("${app.jwt.secret_key}")
    public String appJwtSecretKey;

    @Value("${app.jwt.expiration_time}")
    public long appJwtExpirationTime;

    @Value("${app.jwt.refresh_expiration_time}")
    public long appJwtRefreshExpirationTime;

    @Value("${app.redis.key_prefix}")
    public String appRedisKeyPrefix;
    
}
