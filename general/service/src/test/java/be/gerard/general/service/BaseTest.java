package be.gerard.general.service;

import be.gerard.core.interface_v1.util.AppSessionUtils;
import be.gerard.core.interface_v1.util.UserSessionUtils;
import be.gerard.general.interface_v1.ApplicationAuthenticationService;
import be.gerard.general.interface_v1.UserAuthenticationService;
import be.gerard.general.interface_v1.session.AppSession;
import be.gerard.general.interface_v1.session.UserSession;
import be.gerard.general.service.dao.ApplicationDAO;
import be.gerard.general.service.dao.ApplicationDetailDAO;
import be.gerard.general.service.dao.UserDAO;
import be.gerard.general.service.dao.UserDetailDAO;
import be.gerard.general.service.model.ApplicationDetailRecord;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * BaseTest
 *
 * @author bartgerard
 * @version 0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:be.gerard.general.service.test_initialize.xml", "classpath:be.gerard.general.service.test.xml"})
@Transactional
public class BaseTest {

    protected static final String APP_KEY = "ubrew-test";
    protected static final String APP_NAME = "ubrew-test";
    protected static final String APP_PASS = "testtest";

    protected static final String USER_USERNAME = "bart.gerard@ubrew.be";
    protected static final String USER_PASS = "testtest";
    protected static final String USER_FIRSTNAME = "Bart";
    protected static final String USER_LASTNAME = "Gerard";
    protected static final Date USER_BIRTHDATE = new Date();

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private ApplicationDAO applicationDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    protected ApplicationDetailDAO applicationDetailDAO;

    @Autowired
    protected UserDetailDAO userDetailDAO;

    @Autowired
    protected ApplicationAuthenticationService applicationAuthenticationService;

    @Autowired
    protected UserAuthenticationService userAuthenticationService;

    @Before
    public void setUp() {
        List<ApplicationDetailRecord> applicationDetailRecords = applicationDetailDAO.findAll();
        Assert.assertFalse(applicationDetailRecords.isEmpty());

        AppSession appSession = applicationAuthenticationService.register(APP_KEY, APP_PASS);
        AppSessionUtils.setAppSession(appSession);

        UserSession userSession = userAuthenticationService.login(USER_USERNAME, APP_PASS);
        UserSessionUtils.setUserSession(userSession);
    }

}
