package br.com.blendtecnologia.iam.ui.rest.api.authentication;

import br.com.blendtecnologia.iam.infrastructure.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;

import java.util.concurrent.CompletableFuture;

@Controller
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationService authenticationService;

    @Override
    public CompletableFuture<ResponseEntity<AuthenticationResponse>> authenticate(Authentication authentication)
            throws AuthenticationException {
        var accessToken = authenticationService.authenticate(authentication);
        return CompletableFuture.completedFuture(ResponseEntity.ok(new AuthenticationResponse(accessToken)));
    }
}
