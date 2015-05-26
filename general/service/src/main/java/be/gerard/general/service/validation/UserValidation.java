package be.gerard.general.service.validation;

import be.gerard.core.interface_v1.exception_v1.BusinessException;
import be.gerard.core.interface_v1.validation.Validation;
import be.gerard.core.interface_v1.validation.Validations;
import be.gerard.general.interface_v1.exception.error.UserServiceError;
import be.gerard.general.interface_v1.model.Address;
import be.gerard.general.interface_v1.model.UserDetail;
import be.gerard.general.service.dao.UserDetailDAO;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author bartgerard
 */
@Component
public class UserValidation implements Validation<UserDetail>, Serializable {

    @Value("${be.gerard.general.user.username.pattern}")
    private String usernamePattern;

    @Value(value = "${be.gerard.general.user.password.minLength}")
    private int passwordMinLength;

    @Autowired
    private UserDetailDAO userDAO;

    @Autowired
    private Validations validations;

    @Override
    public boolean isApplicable(Object object) {
        return object instanceof UserDetail;
    }

    @Override
    public void validate(UserDetail user, Map<BusinessException.Error, String[]> errors) {
        // VALIDATIONS
        if (user == null) {
            errors.put(UserServiceError.USER_NULL, new String[]{});
        } else {

            if (!StringUtils.hasText(user.getUsername()) || !Pattern.matches(usernamePattern, user.getUsername())) {
                errors.put(UserServiceError.USERNAME_INVALID, new String[]{user.getUsername()});
            } else if (user.getId() == null && userDAO.getByUsername(user.getUsername()) != null) {
                errors.put(UserServiceError.USERNAME_ALREADY_EXISTS, new String[]{user.getUsername()});
            } else if (user.getId() != null && !Objects.equals(userDAO.find(user.getId()).getUsername(), user.getUsername()) && userDAO.getByUsername(user.getUsername()) != null) {
                errors.put(UserServiceError.USERNAME_ALREADY_EXISTS, new String[]{user.getUsername()});
            }

            if (!StringUtils.hasText(user.getFirstname())) {
                errors.put(UserServiceError.FIRSTNAME_INVALID, new String[]{user.getFirstname()});
            }

            if (!StringUtils.hasText(user.getLastname())) {
                errors.put(UserServiceError.LASTNAME_INVALID, new String[]{user.getLastname()});
            }

            if (user.getBirthDate() == null) {
                errors.put(UserServiceError.BIRTHDATE_INVALID, new String[]{});
            }

            if (!user.getAddresses().isEmpty()) {

                int nbHomeAddress = 0, nbPreferredAddresses = 0;

                for (Address address : user.getAddresses()) {

                    if (address.isHomeAddress()) {
                        nbHomeAddress++;
                    }

                    if (address.isPreferred()) {
                        nbPreferredAddresses++;
                    }

                    validations.validate(address, errors);
                }

                if (nbHomeAddress > 1) {
                    errors.put(UserServiceError.INVALID_NUMBER_OF_HOME_ADDRESSES, new String[]{String.valueOf(nbHomeAddress)});
                }

                if (nbPreferredAddresses > 1) {
                    errors.put(UserServiceError.INVALID_NUMBER_OF_PREFERRED_ADDRESSES, new String[]{String.valueOf(nbPreferredAddresses)});
                }
            }

        }
    }

}
