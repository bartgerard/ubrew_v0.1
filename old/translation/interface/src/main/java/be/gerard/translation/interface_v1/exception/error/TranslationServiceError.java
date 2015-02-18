package be.gerard.translation.interface_v1.exception.error;

import be.gerard.translation.interface_v1.exception.TranslationServiceException;

/**
 *
 * @author bartgerard
 */
public enum TranslationServiceError implements TranslationServiceException.Error {

    TranslationNull(0),
    
    ApplicationInvalid(1),
    LanguageInvalid(2),
    KeyInvalid(3),
    ValueInvalid(4),
    
    SessionInvalid(999);

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
