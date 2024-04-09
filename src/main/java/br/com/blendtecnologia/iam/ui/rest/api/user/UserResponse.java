package br.com.blendtecnologia.iam.ui.rest.api.user;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.entities.UserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.blendtecnologia.iam.infrastructure.persistence.utils.IdConverter.convertId;

public record UserResponse(Long id, UserStatus status, String cpf, String email, String name, String cellphone,
                           @JsonProperty("created_at") LocalDateTime createdAt,
                           @JsonProperty("updated_at") LocalDateTime updatedAt) {

    public static UserResponse from(User user) {
        return new UserResponse(
                convertId(user.getId()),
                user.getStatus(),
                user.getCpf(),
                user.getEmail(),
                user.getName(),
                user.getCellphone(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    public static List<UserResponse> from(List<User> users) {
        return users
                .parallelStream()
                .map(UserResponse::from)
                .toList();
    }
}
