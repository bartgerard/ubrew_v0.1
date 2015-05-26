package be.gerard.general.service.validation;

import be.gerard.core.interface_v1.exception_v1.BusinessException;
import be.gerard.core.interface_v1.validation.Validation;
import be.gerard.general.interface_v1.exception.error.UserServiceError;
import be.gerard.general.interface_v1.model.Address;
import java.io.Serializable;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author bartgerard
 */
@Component
public class AddressValidation implements Validation<Address>, Serializable {

    @Override
    public boolean isApplicable(Object object) {
        return object instanceof Address;
    }

    @Override
    public void validate(final Address address, final Map<BusinessException.Error, String[]> errors) {
        if (address == null) {
            errors.put(UserServiceError.ADDRESS_NULL, new String[]{});
        } else {
            if (!StringUtils.hasText(address.getAddressee())) {
                errors.put(UserServiceError.ADDRESS_ADDRESSEE_INVALID, new String[]{address.getAddressee()});
            }
            if (!StringUtils.hasText(address.getStreet())) {
                errors.put(UserServiceError.ADDRESS_STREET_INVALID, new String[]{address.getStreet()});
            }
            if (address.getHouseNumber() == null) {
                errors.put(UserServiceError.ADDRESS_HOUSE_NUMBER_INVALID, new String[]{});
            }

//            if (!StringUtils.hasText(address.getAddress2())) {
//                errors.put(UserServiceError.AddressLine2Invalid, new String[]{address.getAddress2()});
//            }
            if (!StringUtils.hasText(address.getCity())) {
                errors.put(UserServiceError.ADDRESS_CITY_INVALID, new String[]{address.getCity()});
            }

            if (address.getZipOrPostalCode() == null) {
                errors.put(UserServiceError.ADDRESS_ZIP_INVALID, new String[]{});
            }

            if (!StringUtils.hasText(address.getStateOrProvince())) {
                errors.put(UserServiceError.ADDRESS_STATE_INVALID, new String[]{address.getStateOrProvince()});
            }

            if (!StringUtils.hasText(address.getCountry())) {
                errors.put(UserServiceError.ADDRESS_COUNTRY_INVALID, new String[]{address.getCountry()});
            }
        }
    }

}
