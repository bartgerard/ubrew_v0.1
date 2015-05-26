package be.gerard.common.exception_v1;

import be.gerard.common.exception_v1.error.ServiceError;

import java.util.Map;

/**
 *
 * @author bartgerard
 */
public class TechnicalException extends ServiceException {
    
    public interface Error extends ServiceError {
        
    }

    public TechnicalException(String message, TechnicalException.Error error, String... arguments) {
        super(message, error, arguments);
    }

    public TechnicalException(String message, Map<? extends TechnicalException.Error, String[]> errors) {
        super(message, errors);
    }

}
