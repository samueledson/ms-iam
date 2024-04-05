package br.com.blendtecnologia.iam.ui.rest.api.user;

import static br.com.blendtecnologia.iam.infrastructure.persistence.utils.IdConverter.convertId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.blendtecnologia.iam.core.domain.entities.User;
import br.com.blendtecnologia.iam.core.domain.entities.UserStatus;
import lombok.Value;

@Value
public class UserResponse {
    private final Long id;
    private final UserStatus status;
    private final String cpf;
    private final String email;
    private final String name;
    private final String cellphone;
    @JsonProperty("created_at")
    private final LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private final LocalDateTime updatedAt;

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
                .collect(Collectors.toList());
    }
}
