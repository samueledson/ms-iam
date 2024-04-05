package br.com.blendtecnologia.iam.ui.rest.api.user;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.blendtecnologia.iam.ui.rest.api.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public interface UserController {

    @GetMapping
    @PreAuthorize("hasRole('MS_IAM')")
    CompletableFuture<List<UserResponse>> getAll();

    @PostMapping
    @PreAuthorize("hasRole('MS_IAM')")
    CompletableFuture<ResponseEntity<ApiResponse>> create(HttpServletRequest httpServletRequest,
            @RequestBody @Valid UserRequest userRequest);

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MS_IAM')")
    CompletableFuture<UserResponse> getById(@PathVariable Long id);

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('MS_IAM')")
    CompletableFuture<UserResponse> update(HttpServletRequest httpServletRequest,
                                           @PathVariable Long id, @RequestBody @Valid UpdateUserRequest updateUserRequest);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MS_IAM')")
    CompletableFuture<ResponseEntity<ApiResponse>> delete(@PathVariable Long id);

}
