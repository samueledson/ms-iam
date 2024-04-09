package br.com.blendtecnologia.iam.ui.rest.api.user;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.ui.rest.api.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public final class CreateUserUseCaseOutputMapper {
    
    private CreateUserUseCaseOutputMapper() {
        // Private constructor to hide the implicit public one
    }
    
    public static ResponseEntity<ApiResponse> map(User user, HttpServletRequest httpServletRequest) {
        URI location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest)
                .path("/user/{id}")
                .buildAndExpand(user.getId().number())
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Registered successfully"));
    }

}