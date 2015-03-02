package be.gerard.core.service;

import be.gerard.common.exception_v1.ValidationException;
import be.gerard.core.interface_v1.model.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * UserServiceImplTest
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class UserServiceTest extends BaseTest {

    @Test
    public void validSaveTest() {
        userService.save(user, password);
        
        User userAfterFind = userService.findByUserNameAndPassword(username, password);
        Assert.assertNotNull("userService.findByUserNameAndPassword did return null", userAfterFind);
        Assert.assertEquals(username, userAfterFind.getUsername());
        Assert.assertEquals(firstname, userAfterFind.getFirstname());
        Assert.assertEquals(lastname, userAfterFind.getLastname());
        Assert.assertEquals(birthdate, userAfterFind.getBirthDate());
    }

    @Test(expected = ValidationException.class)
    public void invalidSaveTestUserNull() {
        userService.save(new User(), "123456789");
    }

    @Test(expected = ValidationException.class)
    public void invalidSaveTestPasswordNull() {
        userService.save(user, null);
    }

    @Test(expected = ValidationException.class)
    public void invalidSaveTestPasswordTooShort() {
        userService.save(user, "12345");
    }
    
}
