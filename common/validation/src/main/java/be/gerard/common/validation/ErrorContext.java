package be.gerard.common.validation;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;

/**
 * ErrorContext
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-13 20:44
 */
public class ErrorContext extends BeanPropertyBindingResult implements ErrorBuilder, ErrorDetailBuilder {

    public ErrorContext(Object target, String objectName) {
        super(target, objectName);
    }

    public void addError(boolean condition, String[] errorCodes, Object[] errorArgs, String defaultMessage) {
        if (condition) {
            addError(errorCodes, errorArgs, defaultMessage);
        }
    }

    public void addError(String[] errorCodes, Object[] errorArgs, String defaultMessage) {
        addError(new ObjectError(getObjectName(), errorCodes, errorArgs, defaultMessage));
    }

    @Override
    public ErrorDetailBuilder validate(boolean isValid) {
        if (isValid) {
            return new NoErrorDetailBuilder();
        } else {
            return this;
        }
    }

}
