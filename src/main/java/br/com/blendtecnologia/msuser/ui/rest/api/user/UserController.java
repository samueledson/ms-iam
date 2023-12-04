package br.com.blendtecnologia.msuser.ui.rest.api.user;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blendtecnologia.msuser.ui.rest.api.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public interface UserController {

    @PostMapping
    //@PreAuthorize("hasRole('MS_AUTH')")
    CompletableFuture<ResponseEntity<ApiResponse>> create(HttpServletRequest httpServletRequest,
            @RequestBody @Valid UserRequest request);

}
