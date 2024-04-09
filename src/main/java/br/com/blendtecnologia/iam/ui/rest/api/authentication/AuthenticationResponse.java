package br.com.blendtecnologia.iam.ui.rest.api.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationResponse(@JsonProperty("access_token") String accessToken) {

}