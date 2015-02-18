package be.gerard.core.user.service.validation;

import be.gerard.common.validation.BaseValidator;
import be.gerard.common.validation.ErrorContext;
import be.gerard.core.user.interface_v1.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;

/**
 * UserValidator
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-11 00:52
 */
@Component
public class UserValidator extends BaseValidator<User> {

    @Override
    public void validate(User user, ErrorContext errorContext) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errorContext, "username", "error.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errorContext, "firstname", "error.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errorContext, "lastname", "error.required");
    }

}
