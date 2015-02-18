package be.gerard.common.validation;

import be.gerard.common.exception_v1.BusinessException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

/**
 * Validations
 *
 * @author bartgerard
 */
public class Validations implements Serializable {

    @Value("validation.exception.message")
    private String validationMessage;

    private final Map<String, Validation> validations = new HashMap<>();

    public Validations(final Map<String, Validation> validations) {
        Assert.notNull(validations, "validations is invalid [null]");
        this.validations.putAll(validations);
    }

    public void validate(final Object object) {
        validate(null, object);
    }

    public void validate(final String key, final Object object) {
        Map<BusinessException.Error, String[]> errors = new HashMap<>();
        validate(key, object, errors);
        handle(errors);
    }

    public Map<String, Validation> getValidations() {
        return validations;
    }

    public void handle(final Map<BusinessException.Error, String[]> errors) {
        if (!errors.isEmpty()) {
            throw new BusinessException(validationMessage, errors);
        }
    }

    public void validate(final Object object, final Map<BusinessException.Error, String[]> errors) {
        validate(null, object, errors);
    }

    public void validate(final String key, final Object object, final Map<BusinessException.Error, String[]> errors) {
        if (key == null) {
            validations.values().stream().filter((validation) -> (validation.isApplicable(object))).forEach((validation) -> {
                validation.validate(object, errors);
            });
        } else if (validations.containsKey(key)) {
            if (object instanceof List) {
                validations.get(key).validateList((List) object, errors);
            } else {
                validations.get(key).validate(object, errors);
            }
        }
    }

}
