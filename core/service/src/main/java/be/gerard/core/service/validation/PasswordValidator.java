package be.gerard.core.service.validation;

import be.gerard.common.validation.BaseValidator;
import be.gerard.common.validation.ErrorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author bartgerard
 */
@Component
public class PasswordValidator extends BaseValidator<String> {

    @Value(value = "${be.gerard.general.user.password.minLength:6}")
    private int passwordMinLength;

    public PasswordValidator() {
        super("password");
    }

    @Override
    public void validate(String password, ErrorContext errorContext) {
        errorContext.required(password).reject("error.required");

        if (password != null) {
            errorContext.validate(StringUtils.trimWhitespace(password).length() >= passwordMinLength).reject("error.belowMinLength");
        }
    }

}
