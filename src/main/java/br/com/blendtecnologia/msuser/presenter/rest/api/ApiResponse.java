package br.com.blendtecnologia.msuser.presenter.rest.api;

import lombok.Value;

@Value
public class ApiResponse {
    
    private final Boolean success;
    private final String message;
}
