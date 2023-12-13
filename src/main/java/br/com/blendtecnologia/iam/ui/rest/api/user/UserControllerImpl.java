package br.com.blendtecnologia.iam.ui.rest.api.user;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.blendtecnologia.iam.core.domain.usecases.UseCaseExecutor;
import br.com.blendtecnologia.iam.core.domain.usecases.user.CreateUserUseCase;
import br.com.blendtecnologia.iam.core.domain.usecases.user.GetUserUseCase;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;
import br.com.blendtecnologia.iam.ui.rest.api.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UseCaseExecutor useCaseExecutor;

    private final CreateUserUseCase createUserUseCase;
    private final CreateUserUseCaseInputMapper createUserUseCaseInputMapper;

    private final GetUserUseCase getUserUseCase;

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> create(HttpServletRequest httpServletRequest, @Valid UserRequest userRequest) {

        return useCaseExecutor.execute(
            createUserUseCase,
            createUserUseCaseInputMapper.map(userRequest),
            outputValues -> CreateUserUseCaseOutputMapper.map(outputValues.getUser(), httpServletRequest)
        );
        
    }
    
    @Override
    public CompletableFuture<UserResponse> getById(@PathVariable Long id) {
        return useCaseExecutor.execute(
            getUserUseCase,
            new GetUserUseCase.InputValues(new Identity(id)),
            outputValues -> UserResponse.from(outputValues.getUser())
        );
    }

}
