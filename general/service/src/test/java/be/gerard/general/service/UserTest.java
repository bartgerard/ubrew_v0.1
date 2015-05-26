package be.gerard.general.service;

import be.gerard.core.interface_v1.exception_v1.ServiceException;
import be.gerard.general.interface_v1.UserService;
import be.gerard.general.interface_v1.exception.error.UserServiceError;
import be.gerard.general.interface_v1.model.Address;
import be.gerard.general.interface_v1.model.UserDetail;
import be.gerard.general.interface_v1.session.UserSession;
import be.gerard.general.service.dao.UserDetailDAO;
import be.gerard.general.service.model.AddressRecord;
import be.gerard.general.service.model.UserDetailRecord;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bartgerard
 */
@ActiveProfiles("jpa")
public class UserTest extends BaseTest {

    @Autowired
    private UserDetailDAO userDAO;

    @Value("${be.gerard.general.user.username.pattern}")
    private String pattern;

    @Autowired
    private UserService userService;
    
    @Autowired
    private StrongPasswordEncryptor strongPasswordEncryptor;

    @Test
    public void ping() {
        Assert.assertNotNull(userService.ping());
    }

    @Test
    public void pattern() {
        Assert.assertTrue(Pattern.matches(pattern, "a@a.a"));
        Assert.assertFalse(Pattern.matches(pattern, "@."));
    }
    
    @Test
    public void exception() {
        Assert.assertEquals("UserServiceError" + UserServiceError.KEY_DELIMITER + UserServiceError.INVALID_CREDENTIALS.getCode(), UserServiceError.INVALID_CREDENTIALS.getKey());
        
        //UserServiceException userServiceException = new UserServiceException("user", UserServiceError.FirstnameInvalid, "username");
        //userServiceException.printStackTrace();
    }

    @Test
    @Transactional
    public void saveOrUpdate() {
        UserDetailRecord userRecord = new UserDetailRecord("a", "c", "d", new Date());
        userRecord = userDAO.saveOrUpdate(userRecord);
        Assert.assertNotNull(userRecord.getId());
        UserDetailRecord userRecord2 = userDAO.getByUsername("a");
        Assert.assertEquals(userRecord.getId(), userRecord2.getId());
        Assert.assertEquals(userRecord.getUsername(), userRecord2.getUsername());
        Assert.assertEquals(userRecord.getFirstname(), userRecord2.getFirstname());
        Assert.assertEquals(userRecord.getLastname(), userRecord2.getLastname());
    }

    @Test
    @Transactional
    public void saveOrUpdateWithAddress() {
        UserDetailRecord userRecord = new UserDetailRecord("a", "c", "d", new Date(), new AddressRecord("Bart", "Street", 1, null, "City", 2100, "Antwerp", "Country"));
        userRecord = userDAO.saveOrUpdate(userRecord);
        Assert.assertNotNull(userRecord.getId());
        UserDetailRecord userRecord2 = userDAO.getByUsername("a");
        //Assert.assertEquals(userRecord, userRecord2);
        Assert.assertEquals(userRecord.getId(), userRecord2.getId());
        Assert.assertEquals(userRecord.getUsername(), userRecord2.getUsername());
        Assert.assertEquals(userRecord.getFirstname(), userRecord2.getFirstname());
        Assert.assertEquals(userRecord.getLastname(), userRecord2.getLastname());
        Assert.assertEquals(userRecord.getHomeAddress(), userRecord2.getHomeAddress());
    }

    @Test
    @Transactional
    public void create() throws ServiceException {
        // Hibernate nonUnique exception means --> somewhere two objects use the same id. Solved first time by changing id in creation scripts.
        List<UserDetailRecord> userDetailRecords = userDetailDAO.findAll();
        UserDetail userDetail = userService.saveOrUpdate(new UserDetail("b@b.com", "B", "G", new Date(), new Address("Bart", "Street", 1, null, "City", 2100, "Antwerp", "Country")), "azerty1234567890", APP_KEY);
        Assert.assertNotNull(userDetail.getId());
    }

    @Test
    @Transactional
    public void createNull() throws ServiceException {
        try {
            userService.saveOrUpdate(null, null, APP_KEY);
        } catch (ServiceException e) {
            Assert.assertTrue(e.contains(UserServiceError.USER_NULL));
            Assert.assertTrue(e.contains(UserServiceError.PASSWORD_INVALID));
        }
    }

    @Test
    @Transactional
    public void serviceSaveOrUpdate() throws ServiceException {
        userService.saveOrUpdate(new UserDetail("c@c.com", "B", "G", new Date(), null), "azerty", APP_KEY);
    }

    @Test
    @Transactional
    public void login() throws ServiceException {
        // TODO applicationService save application
        // TODO register application
        userService.saveOrUpdate(new UserDetail("c@c.com", "B", "G", new Date(), new Address("Bart", "Street", 1, null, "City", 2100, "Antwerp", "Country")), "azerty", APP_KEY);
        
        UserSession session = userAuthenticationService.login("c@c.com", "azerty");
        Assert.assertNotNull(session);
    }

    @Test(expected = AuthenticationServiceException.class)
    @Transactional
    public void loginWithInvalidUsernameOrPassword() throws ServiceException {
        userService.saveOrUpdate(new UserDetail("a@a.com", "B", "G", new Date(), new Address("Bart", "Street", 1, null, "City", 2100, "Antwerp", "Country")), "azerty", APP_KEY);
        userAuthenticationService.login("a@a.com", "random");
    }
    
    @Test
    public void encryptPassword() {
        String encryptedPassword = strongPasswordEncryptor.encryptPassword("testtest");
        System.out.println("BGE password: " + encryptedPassword);
    }
    
//    @After
//    public void tearDown() {
//        for (UserRecord userRecord : userDAO.findAll()) {
//            userDAO.delete(userRecord);
//        }
//        
//        Assert.assertTrue(userDAO.findAll().isEmpty());
//    }

}
