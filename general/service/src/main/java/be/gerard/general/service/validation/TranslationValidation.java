package be.gerard.general.service.validation;

import be.gerard.common.exception_v1.BusinessException;
import be.gerard.common.validation.Validation;
import be.gerard.general.interface_v1.exception.error.TranslationServiceError;
import be.gerard.general.interface_v1.model.Translation;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author bartgerard
 */
@Component
public class TranslationValidation implements Validation<Translation>, Serializable {

    @Value("${be.gerard.general.translation.language.pattern}")
    private String languagePattern;

    @Override
    public boolean isApplicable(Object object) {
        return object instanceof Translation;
    }

    @Override
    public void validate(Translation translation, Map<BusinessException.Error, String[]> errors) {
        if (translation == null) {
            errors.put(TranslationServiceError.TRANSLATION_NULL, new String[]{});
        } else {
            if (!StringUtils.hasText(translation.getApplication())) {
                errors.put(TranslationServiceError.APPLICATION_INVALID, new String[]{translation.getApplication()});
            }
            if (translation.getLanguage() == null || !Pattern.matches(languagePattern, translation.getLanguage())) {
                errors.put(TranslationServiceError.LANGUAGE_INVALID, new String[]{Objects.toString(translation.getLanguage())});
            }
            if (!StringUtils.hasText(translation.getKey())) {
                errors.put(TranslationServiceError.KEY_INVALID, new String[]{translation.getKey()});
            }
            if (!StringUtils.hasText(translation.getValue())) {
                errors.put(TranslationServiceError.VALUE_INVALID, new String[]{translation.getValue()});
            }
        }
    }

}
