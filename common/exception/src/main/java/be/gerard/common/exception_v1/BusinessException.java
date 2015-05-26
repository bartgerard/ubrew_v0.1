package be.gerard.common.exception_v1;

import be.gerard.common.exception_v1.error.ServiceError;

import java.util.Map;

/**
 *
 * @author bartgerard
 */
public class BusinessException extends ServiceException {
    
    public interface Error extends ServiceError {
        
    }

    public BusinessException(String message, BusinessException.Error error, String... arguments) {
        super(message, error, arguments);
    }

    public BusinessException(String message, Map<? extends BusinessException.Error, String[]> errors) {
        super(message, errors);
    }

}
