package be.gerard.core.interface_v1;

import be.gerard.common.annotation.validation.Validate;
import be.gerard.core.interface_v1.model.User;

import java.util.List;

/**
 * UserService
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface UserService {

    User save(@Validate User user, @Validate(type = "password") String password);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    List<User> findAll();

}
