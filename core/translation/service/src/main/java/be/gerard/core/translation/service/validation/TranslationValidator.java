package be.gerard.core.translation.service.validation;

import be.gerard.common.validation.BaseValidator;
import be.gerard.common.validation.ErrorContext;
import be.gerard.core.translation.interface_v1.model.Translation;
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
public class TranslationValidator extends BaseValidator<Translation> {

    @Override
    public void validate(Translation translation, ErrorContext errorContext) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errorContext, "application", "error.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errorContext, "language", "error.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errorContext, "key", "error.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errorContext, "value", "error.required");
    }

}
