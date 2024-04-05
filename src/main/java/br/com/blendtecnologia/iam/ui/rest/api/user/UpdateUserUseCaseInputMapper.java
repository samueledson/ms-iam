package br.com.blendtecnologia.iam.ui.rest.api.user;

import org.springframework.stereotype.Service;

import br.com.blendtecnologia.iam.core.domain.usecases.user.UpdateUserUseCase;
import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;

import java.util.Optional;

@Service
public final class UpdateUserUseCaseInputMapper {

    public UpdateUserUseCase.InputValues map(Long id, UpdateUserRequest updateUserRequest) {
        return new UpdateUserUseCase.InputValues(
            new Identity(id),
            Optional.ofNullable(updateUserRequest.getCpf()),
            Optional.ofNullable(updateUserRequest.getEmail()),
            Optional.ofNullable(updateUserRequest.getPassword()),
            Optional.ofNullable(updateUserRequest.getName()),
            Optional.ofNullable(updateUserRequest.getCellphone())
        );
    }

}
