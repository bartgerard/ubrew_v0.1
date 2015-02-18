package be.gerard.ubrew.core.interface_v1.exception;

import be.gerard.common.exception_v1.BusinessException;
import java.io.Serializable;
import java.util.Map;

/**
 * BeerServiceException
 * 
 * @author bartgerard
 */
public class BeerValidationException extends BusinessException implements Serializable {
    
    public interface Error extends BusinessException.Error {
        
    }

    public BeerValidationException(String message, BeerValidationException.Error error, String... arguments) {
        super(message, error, arguments);
    }

    public BeerValidationException(String message, Map<? extends BeerValidationException.Error, String[]> errors) {
        super(message, errors);
    }
    
}
