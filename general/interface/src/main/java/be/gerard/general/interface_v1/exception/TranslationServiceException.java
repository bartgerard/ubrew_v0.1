package be.gerard.general.interface_v1.exception;

import be.gerard.core.interface_v1.exception_v1.BusinessException;
import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author bartgerard
 */
public class TranslationServiceException extends BusinessException implements Serializable {
    
    public interface Error extends BusinessException.Error {
        
    }

    public TranslationServiceException(String message, TranslationServiceException.Error error, String... arguments) {
        super(message, error, arguments);
    }

    public TranslationServiceException(String message, Map<? extends TranslationServiceException.Error, String[]> errors) {
        super(message, errors);
    }
    
}
