package br.com.blendtecnologia.iam.core.domain.exceptions;

public class CpfAlreadyUsedException extends DomainException {
    
    public CpfAlreadyUsedException(String message) {
        super(message);
    }

}
