package br.com.blendtecnologia.iam.ui.rest.api.user;

import br.com.blendtecnologia.iam.ui.rest.api.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("users")
public interface UserController {

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_user:create')")
    CompletableFuture<ResponseEntity<ApiResponse>> create(HttpServletRequest httpServletRequest,
            @RequestBody @Valid UserRequest userRequest);

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_user:read')")
    CompletableFuture<UserResponse> getById(@PathVariable Long id);

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_user:update')")
    CompletableFuture<UserResponse> update(HttpServletRequest httpServletRequest, @PathVariable Long id,
                                           @RequestBody @Valid UpdateUserRequest updateUserRequest);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_user:delete')")
    CompletableFuture<ResponseEntity<ApiResponse>> delete(@PathVariable Long id);

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_users:list')")
    CompletableFuture<List<UserResponse>> getAll();

}
