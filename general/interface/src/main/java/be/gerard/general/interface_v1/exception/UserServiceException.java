package be.gerard.general.interface_v1.exception;

import be.gerard.common.exception_v1.BusinessException;
import java.util.Map;

/**
 *
 * @author bartgerard
 */
public class UserServiceException extends BusinessException {
    
    public interface Error extends BusinessException.Error {
        
    }

    public UserServiceException(String message, UserServiceException.Error error, String... arguments) {
        super(message, error, arguments);
    }

    public UserServiceException(String message, Map<? extends UserServiceException.Error, String[]> errors) {
        super(message, errors);
    }
    
}
