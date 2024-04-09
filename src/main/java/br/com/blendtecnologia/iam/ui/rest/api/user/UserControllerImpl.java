package br.com.blendtecnologia.iam.ui.rest.api.user;

import br.com.blendtecnologia.iam.core.domain.usecases.UseCaseExecutor;
import br.com.blendtecnologia.iam.core.domain.usecases.user.*;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
import br.com.blendtecnologia.iam.ui.rest.api.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UseCaseExecutor useCaseExecutor;

    private final GetAllUsersUseCase getAllUsersUseCase;

    private final CreateUserUseCase createUserUseCase;
    private final CreateUserUseCaseInputMapper createUserUseCaseInputMapper;

    private final GetUserUseCase getUserUseCase;

    private final UpdateUserUseCase updateUserUseCase;
    private final UpdateUserUseCaseInputMapper updateUserUseCaseInputMapper;

    private final DeleteUserUseCase deleteUserUseCase;

    @Override
    public CompletableFuture<List<UserResponse>> getAll() {
        return useCaseExecutor.execute(
            getAllUsersUseCase,
            new GetAllUsersUseCase.InputValues(),
            outputValues -> UserResponse.from(outputValues.users())
        );
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> create(HttpServletRequest httpServletRequest,
                                                                 @Valid UserRequest userRequest) {

        return useCaseExecutor.execute(
            createUserUseCase,
            createUserUseCaseInputMapper.map(userRequest),
            outputValues -> CreateUserUseCaseOutputMapper.map(outputValues.user(), httpServletRequest)
        );
        
    }
    
    @Override
    public CompletableFuture<UserResponse> getById(@PathVariable Long id) {
        return useCaseExecutor.execute(
            getUserUseCase,
            new GetUserUseCase.InputValues(new Identity(id)),
            outputValues -> UserResponse.from(outputValues.user())
        );
    }

    @Override
    public CompletableFuture<UserResponse> update(HttpServletRequest httpServletRequest, @PathVariable Long id,
                                                  @Valid UpdateUserRequest updateUserRequest) {
        return useCaseExecutor.execute(
            updateUserUseCase,
            updateUserUseCaseInputMapper.map(id, updateUserRequest),
            outputValues -> UserResponse.from(outputValues.user()));
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> delete(Long id) {
        return useCaseExecutor.execute(
                deleteUserUseCase,
                new DeleteUserUseCase.InputValues(new Identity(id)),
                outputValues -> ResponseEntity.ok(new ApiResponse(true, "Deleted successfully"))
        );
    }

}
