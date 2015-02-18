package be.gerard.common.exception_v1.error;

import be.gerard.common.exception_v1.TechnicalException;

/**
 *
 * @author bartgerard
 */
public enum CommonTechnicalError implements TechnicalException.Error {
    
    UNEXPECTED(9998),
    UNKNOWN(9999);

    private CommonTechnicalError(int code) {
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
