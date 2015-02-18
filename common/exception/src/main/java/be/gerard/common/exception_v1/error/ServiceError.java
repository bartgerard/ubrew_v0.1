package be.gerard.common.exception_v1.error;

import java.io.Serializable;

/**
 *
 * @author bartgerard
 */
public interface ServiceError extends Serializable {

    String KEY_DELIMITER = ".";
    
    default String getPrefix() {
        return getClass().getSimpleName();
    }
    
    int getCode();
    
    default String getKey() {
        return String.format("%s%s%s", getPrefix(), KEY_DELIMITER, getCode());
    }
    
    public String name();
    
}
