package be.gerard.core.service.script;

import be.gerard.core.interface_v1.ApplicationService;
import be.gerard.core.interface_v1.model.User;
import be.gerard.core.interface_v1.session.UserSession;
import be.gerard.core.interface_v1.util.UserSessionUtils;
import be.gerard.core.service.builder.BuilderContext;
import be.gerard.core.service.config.CoreServiceConfig;
import be.gerard.core.service.config.DefaultDataBaseConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;

/**
 * ApplicationScript
 *
 * @author bartgerard
 * @version v0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreServiceConfig.class, DefaultDataBaseConfig.class})
@PropertySource("classpath:be.gerard.core.service.test.properties")
@Transactional
public class ApplicationScript {

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
    private BuilderContext builderContext;

    @Autowired
    private ApplicationService applicationService;

    @Before
    public void before() {
        // TODO DELETE!!!
        User user = new User();
        user.setUsername(USERNAME);
        UserSession userSession = new UserSession(user);
        UserSessionUtils.setUserSession(userSession);

        // User
        this.user = new User(username);

        this.user.setFirstname(firstname);
        this.user.setLastname(lastname);
        this.user.setBirthDate(birthdate);
        this.user.getEmails().add(email);
    }

    @Test
    @Rollback(false)
    public void initApplications() {
        builderContext.load();

        builderContext.buildUser("bgerard")
                .firstname("FIRSTNAME")
                .lastname("LASTNAME")
                .birthDate(LocalDate.of(1988, Month.MAY, 3))
                .password("PASSWORD")
                .save();

        builderContext.buildApplication("core.web")
                .property("be.gerard.core.service.url", "urls", "http://localhost:8080/core-service")
                .build()
                .save();
    }

}
