
package be.gerard.core.user.interface_v1;

import be.gerard.common.annotation.validation.Validate;
import be.gerard.core.user.interface_v1.model.User;

/**
 * UserService
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface UserService {

    User save(@Validate User user, @Validate(type = "password") String password);

    User findByUserNameAndPassword(String username, String password);

}
