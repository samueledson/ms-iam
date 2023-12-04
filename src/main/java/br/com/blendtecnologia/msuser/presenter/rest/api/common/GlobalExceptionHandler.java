package br.com.blendtecnologia.msuser.presenter.rest.api.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import br.com.blendtecnologia.msuser.core.domain.exceptions.CpfAlreadyUsedException;
import br.com.blendtecnologia.msuser.core.domain.exceptions.DomainException;
import br.com.blendtecnologia.msuser.presenter.rest.api.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex) {
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {JpaSystemException.class})
    ResponseEntity<ApiResponse> handleJpaSystemException(JpaSystemException ex) {
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {DomainException.class})
    ResponseEntity<ApiResponse> handleDomainException(DomainException ex) {
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CpfAlreadyUsedException.class})
    ResponseEntity<ApiResponse> handleCpfAlreadyUsedException(CpfAlreadyUsedException ex) {
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
