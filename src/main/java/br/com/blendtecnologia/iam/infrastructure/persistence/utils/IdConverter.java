package br.com.blendtecnologia.iam.infrastructure.persistence.utils;

import br.com.blendtecnologia.iam.core.domain.valueobjects.Identity;

public final class IdConverter {
    
    private IdConverter() {
        // private constructor to hide the implicit public one
    }
    
    public static Long convertId(Identity id) {
        if(id != null && id.number() != Long.MIN_VALUE) {
            return id.number();
        }
        return null;
    }

}
