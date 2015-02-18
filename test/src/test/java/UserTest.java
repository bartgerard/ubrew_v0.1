import be.gerard.common.exception_v1.ServiceException;
import be.gerard.general.interface_v1.UserAuthenticationService;
import be.gerard.general.interface_v1.UserService;
import be.gerard.general.interface_v1.exception.error.UserServiceError;
import be.gerard.general.interface_v1.model.Address;
import be.gerard.general.interface_v1.model.UserDetail;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author bartgerard
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-config.xml")
public class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    //@Value("${be.gerard.general.application.name}")
    private final String applicationName = "test";
    
    @Test
    public void echo() {
        String result = userService.ping();
        Assert.assertNotNull(result);
    }
    
//    @Test
//    public void validate() {
//        Assert.assertFalse(userAuthenticationService.validate(new UserSession(new UserDetail(1L, "a", "b", "c", new Date(), new Address("Bart", "Street", 1, null, "City", 2100, "Antwerp", "Country")))));
//    }
    
    @Test
    public void create() throws ServiceException {
        UserDetail user = userService.saveOrUpdate(new UserDetail("bart.gerard@gmail.com", "Bart", "Gerard", new Date(), new Address("Bart", "Street", 1, null, "City", 2100, "Antwerp", "Country")), "azerty", applicationName);
        Assert.assertNotNull(user.getId());
    }
    
    @Test
    public void loginWithInvalidUsernameOrPassword() {
        try {
            userService.saveOrUpdate(new UserDetail("a@a.com", "a", "a", new Date(), new Address("Bart", "Street", 1, null, "City", 2100, "Antwerp", "Country")), "azerty", applicationName);
            userAuthenticationService.login("a@a.com", "random");
        } catch (ServiceException e) {
            Assert.assertTrue(e.contains(UserServiceError.INVALID_TOKEN));
        }
    }
    
}
