package be.gerard.ubrew.core.interface_v1.exception.error;

import be.gerard.general.interface_v1.exception.UserServiceException;
import java.io.Serializable;

/**
 * BeerValidationError
 * 
 * @author bartgerard
 */
public enum BeerValidationError implements UserServiceException.Error, Serializable {

    NULL(0),
    TYPE_NULL(1),
    TYPE_INVALID(2);

    private BeerValidationError(int code) {
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
