package br.com.blendtecnologia.iam.core.domain.entities;

import lombok.Getter;

@Getter
public enum UserStatus {

    INACTIVE(0L),
    ACTIVE(1L),
    PENDING(2L),
    BLOCKED(3L);

    private final Long value;

    UserStatus(Long value) {
        this.value = value;
    }
}
