package br.com.blendtecnologia.iam.core.domain.entities;

public enum UserStatus {
    INACTIVE(0L),
    ACTIVE(1L),
    PENDING(2L),
    BLOCKED(3L);

    private Long value;

    UserStatus(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
    
}
