package be.gerard.core.service;

import be.gerard.common.authentication.util.UserSessionUtils;
import be.gerard.core.interface_v1.UserService;
import be.gerard.core.interface_v1.model.User;
import be.gerard.general.interface_v1.model.UserDetail;
import be.gerard.general.interface_v1.session.UserSession;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;

/**
 * BaseTest
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-10 19:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:be.gerard.core.service.test.xml"})
@Transactional
public class BaseTest {

    public static final String USERNAME = "bgerard";
    public static final String PASSWORD = "password";
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";
    public static final LocalDate BIRTHDATE = LocalDate.of(1988, Month.MAY, 3);
    public static final String APP_KEY = "key_123";
    public static final String REFERENCE = "instance_1";
    public static final String IP = "127.0.0.1";
    public static final String MAC = "AB:CD:EF:GH";
    public static final String PROP1_KEY = "property1";
    public static final String PROP1_GROUP = "group1";
    public static final String PROP1_VALUE = "value1";

    protected final String username = "bgerard";
    protected final String password = "password";
    protected final String firstname = "FIRSTNAME";
    protected final String lastname = "LASTNAME";
    protected final LocalDate birthdate = LocalDate.of(1988, Month.MAY, 3);
    protected final String email = "EMAIL";
    protected User user;

    @Autowired
    protected UserService userService;

    @Before
    public void before() {
        // TODO DELETE!!!
        UserDetail userDetail = new UserDetail();
        userDetail.setUsername(USERNAME);
        UserSession userSession = new UserSession(userDetail);
        UserSessionUtils.setUserSession(userSession);

        // User
        user = new User(username);

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setBirthDate(birthdate);
        user.getEmails().add(email);
    }

}
