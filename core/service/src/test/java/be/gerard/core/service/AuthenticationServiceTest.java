package be.gerard.core.service;

import be.gerard.core.interface_v1.AuthenticationService;
import be.gerard.core.interface_v1.session.UserSession;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AuthenticationServiceTest
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-02-03 19:44
 */
public class AuthenticationServiceTest extends BaseTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void test() {
        userService.save(user, password);

        UserSession userSession = authenticationService.login(username, password);

        Assert.assertNotNull(userSession);
    }

}
