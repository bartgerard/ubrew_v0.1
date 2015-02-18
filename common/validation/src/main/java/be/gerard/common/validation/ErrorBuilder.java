package be.gerard.common.validation;

import org.springframework.util.StringUtils;

/**
 * ErrorBuilder
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-27 21:36
 */
public interface ErrorBuilder {

    ErrorDetailBuilder validate(boolean isValid);

    default ErrorDetailBuilder required(Object object) {
        return validate(object != null);
    }

    default ErrorDetailBuilder required(String string) {
        return validate(StringUtils.hasText(string));
    }

}
