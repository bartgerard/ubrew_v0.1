package be.gerard.core.service.builder;

import be.gerard.core.service.model.RoleRecord;
import be.gerard.core.service.model.UserRecord;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * UserBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class UserBuilder extends Builder<UserRecord> {

    private final Set<RoleRecord> roles = new HashSet<>();

    public UserBuilder(UserRecord userRecord, BuilderContext builderContext) {
        super(userRecord, builderContext);
    }

    public UserBuilder password(String password) {
        Assert.hasText(password, "user.password is invalid [null]");
        getRecord().setEncryptedPassword(getBuilderContext().getPasswordEncryptor().encryptPassword(password));
        return this;
    }

    public UserBuilder firstname(String firstname) {
        Assert.hasText(firstname, "user.firstname is invalid [null]");
        getRecord().setFirstname(firstname);
        return this;
    }

    public UserBuilder lastname(String lastname) {
        Assert.hasText(lastname, "user.firstname is invalid [null]");
        getRecord().setLastname(lastname);
        return this;
    }

    public UserBuilder birthDate(LocalDate birthDate) {
        Assert.notNull(birthDate, "user.birthDate is invalid [null]");
        getRecord().setBirthDate(birthDate);
        return this;
    }

    public UserBuilder assignRole(String roleName) {
        Assert.notNull(roleName, "user.role is invalid [null]");
        roles.add(getBuilderContext().getOrCreateRole(roleName));
        return this;
    }

    public UserBuilder build() {
        getRecord().getRoles().clear();
        getRecord().getRoles().addAll(roles);
        return this;
    }

    public void save() {
        getBuilderContext().save(getRecord());
    }

}
