package be.gerard.core.service.builder;

import be.gerard.core.service.model.UserRecord;
import org.springframework.util.Assert;

import java.time.LocalDate;

/**
 * UserBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class UserBuilder {

    private final UserRecord userRecord;

    private final BuilderContext builderContext;

    public UserBuilder(UserRecord userRecord, BuilderContext builderContext) {
        this.userRecord = userRecord;
        this.builderContext = builderContext;
    }

    public UserBuilder password(String password) {
        Assert.hasText(password, "user.password is invalid [null]");
        userRecord.setEncryptedPassword(password);
        return this;
    }

    public UserBuilder firstname(String firstname) {
        Assert.hasText(firstname, "user.firstname is invalid [null]");
        userRecord.setFirstname(firstname);
        return this;
    }

    public UserBuilder lastname(String lastname) {
        Assert.hasText(lastname, "user.firstname is invalid [null]");
        userRecord.setLastname(lastname);
        return this;
    }

    public UserBuilder birthDate(LocalDate birthDate) {
        Assert.notNull(birthDate, "user.birthDate is invalid [null]");
        userRecord.setBirthDate(birthDate);
        return this;
    }

    public void save() {
        builderContext.save(userRecord);
    }

}
