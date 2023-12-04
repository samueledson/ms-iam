package br.com.blendtecnologia.msuser.ui.rest.api.user;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import br.com.blendtecnologia.msuser.core.domain.usecases.UseCaseExecutor;
import br.com.blendtecnologia.msuser.core.domain.usecases.user.CreateUserUseCase;
import br.com.blendtecnologia.msuser.ui.rest.api.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UseCaseExecutor useCaseExecutor;
    private final CreateUserUseCase createUserUseCase;
    private final CreateUserUseCaseInputMapper createUserUseCaseInputMapper;

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> create(HttpServletRequest httpServletRequest, @Valid UserRequest userRequest) {

        return useCaseExecutor.execute(
            createUserUseCase,
            createUserUseCaseInputMapper.map(userRequest),
            outputValues -> CreateUserUseCaseOutputMapper.map(outputValues.getUser(), httpServletRequest)
        );
        
    }
    

    

}
