package be.gerard.general.service.validation;

import be.gerard.core.interface_v1.exception_v1.BusinessException;
import be.gerard.core.interface_v1.validation.Validation;
import be.gerard.general.interface_v1.exception.error.UserServiceError;
import java.io.Serializable;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author bartgerard
 */
@Component
public class PasswordValidation implements Validation<String>, Serializable {

    @Value(value = "${be.gerard.general.user.password.minLength}")
    private int passwordMinLength;

    @Override
    public boolean isApplicable(Object object) {
        return false; // Must be named
    }

    @Override
    public void validate(String password, Map<BusinessException.Error, String[]> errors) {
        if (!StringUtils.hasText(password)) {
            errors.put(UserServiceError.PASSWORD_INVALID, new String[]{});
        } else if (StringUtils.trimWhitespace(password).length() < passwordMinLength) {
            errors.put(UserServiceError.PASSWORD_2_SIMPLE, new String[]{});
        }
    }

}
