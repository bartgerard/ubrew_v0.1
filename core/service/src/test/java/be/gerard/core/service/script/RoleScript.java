package be.gerard.core.service.script;

import be.gerard.core.interface_v1.model.User;
import be.gerard.core.interface_v1.session.UserSession;
import be.gerard.core.interface_v1.util.UserSessionUtils;
import be.gerard.core.service.builder.BuilderContext;
import be.gerard.core.service.config.CoreServiceConfig;
import be.gerard.core.service.config.DefaultDataBaseConfig;
import org.junit.Assert;
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
public class RoleScript {

    public static final String USERNAME = "bgerard";

    protected final String username = "bgerard";
    protected final String firstname = "FIRSTNAME";
    protected final String lastname = "LASTNAME";
    protected final LocalDate birthdate = LocalDate.of(1988, Month.MAY, 3);
    protected final String email = "EMAIL";

    protected User user;

    @Autowired
    private BuilderContext builderContext;

    @Before
    public void before() {
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
    public void testpassword() {
        String password = builderContext.getOrCreateUser("bgerard").getEncryptedPassword();
        Assert.assertTrue(builderContext.getPasswordEncryptor().checkPassword("PASSWORD", password));
    }

    @Test
    @Rollback(false)
    public void initRoles() {
        builderContext.buildRole("USER")
                .build();

        builderContext.buildRole("ADMIN")
                .addPrivilege("CRUD_USER")
                .addPrivilege("CRUD_TRANSLATION")
                .build();

        builderContext.buildUser(USERNAME)
                .firstname("firstname")
                .lastname("lastname")
                .birthDate(LocalDate.of(1988, Month.MAY, 3))
                .password("password")
                .assignRole("ADMIN")
                .build();

        builderContext.saveAll();
    }

}
