package be.gerard.general.interface_v1.exception.error;

import be.gerard.general.interface_v1.exception.UserServiceException;
import java.io.Serializable;

/**
 *
 * @author bartgerard
 */
public enum UserServiceError implements UserServiceException.Error, Serializable {

    USER_NULL(0),
    
    USERNAME_INVALID(1),
    USERNAME_ALREADY_EXISTS(2),
    
    PASSWORD_INVALID(3),
    PASSWORD_2_SIMPLE(4),
    
    FIRSTNAME_INVALID(5),
    
    LASTNAME_INVALID(6),
    
    BIRTHDATE_INVALID(7),
    
    ADDRESS_NULL(8),
    
    ADDRESS_ADDRESSEE_INVALID(8),
    ADDRESS_STREET_INVALID(9),
    ADDRESS_HOUSE_NUMBER_INVALID(10),
    ADDRESS_CITY_INVALID(11),
    ADDRESS_ZIP_INVALID(12), // OR POSTALCODE
    ADDRESS_STATE_INVALID(13), // OR PROVINCE
    ADDRESS_COUNTRY_INVALID(14),
    INVALID_NUMBER_OF_HOME_ADDRESSES(15),
    INVALID_NUMBER_OF_PREFERRED_ADDRESSES(16),
    
    INVALID_CREDENTIALS(100),
    INVALID_TOKEN(101);

    private UserServiceError(int code) {
        this.code = code;
    }

    private final int code;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return getKey();
    }

}
