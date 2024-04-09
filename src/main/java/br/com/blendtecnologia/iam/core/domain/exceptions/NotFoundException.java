package br.com.blendtecnologia.iam.core.domain.exceptions;

public class NotFoundException extends DomainException {

    public NotFoundException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }

}
