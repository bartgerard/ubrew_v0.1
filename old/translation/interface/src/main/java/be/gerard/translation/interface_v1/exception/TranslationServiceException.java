package be.gerard.translation.interface_v1.exception;

import be.gerard.common.exception_v1.BusinessException;
import java.util.Map;

/**
 *
 * @author bartgerard
 */
public class TranslationServiceException extends BusinessException {
    
    public interface Error extends BusinessException.Error {
        
    }

    public TranslationServiceException(String message, TranslationServiceException.Error error, String... arguments) {
        super(message, error, arguments);
    }

    public TranslationServiceException(String message, Map<? extends TranslationServiceException.Error, String[]> errors) {
        super(message, errors);
    }
    
}
