package br.com.blendtecnologia.iam.ui.rest.api.user;

import br.com.blendtecnologia.iam.ui.rest.api.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("users")
public interface UserController {

    @GetMapping
    @PreAuthorize("hasRole('MS_IAM_READ_USERS')")
    CompletableFuture<List<UserResponse>> getAll();

    @PostMapping
    @PreAuthorize("hasRole('MS_IAM')")
    CompletableFuture<ResponseEntity<ApiResponse>> create(HttpServletRequest httpServletRequest,
            @RequestBody @Valid UserRequest userRequest);

    @GetMapping("/{id}")
    @Secured("MS_IAM_READ_USER")
    CompletableFuture<UserResponse> getById(@PathVariable Long id);

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('MS_IAM')")
    CompletableFuture<UserResponse> update(HttpServletRequest httpServletRequest, @PathVariable Long id,
                                           @RequestBody @Valid UpdateUserRequest updateUserRequest);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MS_IAM')")
    CompletableFuture<ResponseEntity<ApiResponse>> delete(@PathVariable Long id);

}
