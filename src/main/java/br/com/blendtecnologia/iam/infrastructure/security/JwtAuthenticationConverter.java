package br.com.blendtecnologia.iam.infrastructure.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        /*String scope = jwt.getClaim("scope").toString();
        List<String> scopeList = Arrays.asList(scope.split("\\s+"));
        Collection<? extends GrantedAuthority> authorities = scopeList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());*/

        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("MS_IAM"));

        return new JwtAuthenticationToken(jwt, authorities);
    }

}
