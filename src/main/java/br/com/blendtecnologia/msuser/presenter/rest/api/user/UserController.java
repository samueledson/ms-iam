package br.com.blendtecnologia.msuser.presenter.rest.api.user;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import br.com.blendtecnologia.msuser.core.domain.usecases.UseCaseExecutor;
import br.com.blendtecnologia.msuser.core.domain.usecases.user.CreateUserUseCase;
import br.com.blendtecnologia.msuser.presenter.rest.api.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController implements UserResource {

    private final UseCaseExecutor useCaseExecutor;
    private final CreateUserUseCase createUserUseCase;
    private final CreateUserInputMapper createUserInputMapper;

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> create(HttpServletRequest httpServletRequest, @Valid UserRequest userRequest) {

        return useCaseExecutor.execute(
            createUserUseCase,
            createUserInputMapper.map(userRequest),
            outputValues -> CreateUserUseCaseOutputMapper.map(outputValues.getUser(), httpServletRequest)
        );
        
    }
    

    

}
