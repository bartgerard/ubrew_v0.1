package be.gerard.core.service.script;

import be.gerard.core.interface_v1.model.User;
import be.gerard.core.interface_v1.session.UserSession;
import be.gerard.core.interface_v1.util.UserSessionUtils;
import be.gerard.core.service.builder.BuilderContext;
import be.gerard.core.service.config.CoreServiceConfig;
import be.gerard.core.service.config.DefaultDataBaseConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;

/**
 * CommonScript
 *
 * @author bartgerard
 * @version v0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreServiceConfig.class, DefaultDataBaseConfig.class})
@PropertySource("classpath:be.gerard.core.service.test.properties")
@Transactional
public class CommonScript {

    protected final String username = "bgerard";
    protected final String firstname = "firstname";
    protected final String lastname = "lastname";
    protected final LocalDate birthdate = LocalDate.of(1988, Month.MAY, 3);
    protected final String email = "email";

    protected User user;

    @Autowired
    protected BuilderContext builderContext;

    @Before
    public void before() {
        User user = new User();
        user.setUsername(username);
        UserSession userSession = new UserSession(user);
        UserSessionUtils.setUserSession(userSession);

        // User
        this.user = new User(username);

        this.user.setFirstname(firstname);
        this.user.setLastname(lastname);
        this.user.setBirthDate(birthdate);
        this.user.getEmails().add(email);
    }

}
