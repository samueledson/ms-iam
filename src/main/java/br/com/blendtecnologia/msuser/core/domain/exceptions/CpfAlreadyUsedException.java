package br.com.blendtecnologia.msuser.core.domain.exceptions;

public class CpfAlreadyUsedException extends DomainException {
    
    public CpfAlreadyUsedException(String message) {
        super(message);
    }

}
