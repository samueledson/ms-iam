package br.com.blendtecnologia.iam.ui.rest.api.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("authenticate")
public interface AuthenticationController {

    @PostMapping
    CompletableFuture<ResponseEntity<AuthenticationResponse>> authenticate(Authentication authentication)
            throws AuthenticationException;

}
