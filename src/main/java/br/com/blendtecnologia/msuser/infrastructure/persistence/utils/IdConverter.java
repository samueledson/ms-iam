package br.com.blendtecnologia.msuser.infrastructure.persistence.utils;

import br.com.blendtecnologia.msuser.core.domain.valueobjects.Identity;

public final class IdConverter {
    
    private IdConverter() {
        // private constructor to hide the implicit public one
    }
    
    public static Long convertId(Identity id) {
        if(id != null && id.getNumber() != Long.MIN_VALUE) {
            return id.getNumber();
        }
        return null;
    }

}
