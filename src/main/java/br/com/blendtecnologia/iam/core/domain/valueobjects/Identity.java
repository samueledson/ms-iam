package br.com.blendtecnologia.iam.core.domain.valueobjects;

public record Identity(Long number) {

    public static Identity nothing() {
        return new Identity(Long.MIN_VALUE);
    }
}
