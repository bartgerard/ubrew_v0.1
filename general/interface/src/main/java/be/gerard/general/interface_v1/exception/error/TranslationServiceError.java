package be.gerard.general.interface_v1.exception.error;

import be.gerard.general.interface_v1.exception.TranslationServiceException;
import java.io.Serializable;

/**
 *
 * @author bartgerard
 */
public enum TranslationServiceError implements TranslationServiceException.Error, Serializable {

    TRANSLATION_NULL(0),
    
    APPLICATION_INVALID(1),
    LANGUAGE_INVALID(2),
    KEY_INVALID(3),
    VALUE_INVALID(4),
    IMPORT_FAILED(5),
    
    SESSION_INVALID(999);

    private TranslationServiceError(int code) {
        this.code = code;
    }

    private final int code;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return getKey();
    }

}
