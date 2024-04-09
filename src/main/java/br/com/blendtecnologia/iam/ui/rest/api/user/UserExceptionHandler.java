package br.com.blendtecnologia.iam.ui.rest.api.user;

import br.com.blendtecnologia.iam.core.domain.exceptions.CpfAlreadyUsedException;
import br.com.blendtecnologia.iam.ui.rest.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    
    @ExceptionHandler(value = {CpfAlreadyUsedException.class})
    ResponseEntity<ApiResponse> handleCpfAlreadyUsedException(CpfAlreadyUsedException ex) {
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
