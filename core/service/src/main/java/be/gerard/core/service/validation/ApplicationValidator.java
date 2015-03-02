package be.gerard.core.service.validation;

import be.gerard.common.validation.BaseValidator;
import be.gerard.common.validation.ErrorContext;
import be.gerard.core.interface_v1.model.Application;
import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;

/**
 * ApplicationValidator
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-11 00:52
 */
@Component
public class ApplicationValidator extends BaseValidator<Application> {

    @Override
    public void validate(Application application, ErrorContext errorContext) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errorContext, "key", "error.key.required");
    }

}
