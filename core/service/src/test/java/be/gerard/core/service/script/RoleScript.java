package be.gerard.core.service.script;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

/**
 * ApplicationScript
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class RoleScript extends CommonScript {

    @Test
    @Rollback(false)
    public void initRoles() {
        builderContext.buildRole("USER")
                .build();

        builderContext.buildRole("ADMIN")
                .addPrivilege("CRUD_USER")
                .addPrivilege("CRUD_TRANSLATION")
                .build();

        builderContext.buildRole("TEST")
                .build();

        builderContext.buildUser(username)
                .firstname(firstname)
                .lastname(lastname)
                .birthDate(birthdate)
                .password("password")
                .assignRole("ADMIN")
                .build();

        builderContext.saveAll();
    }

}
