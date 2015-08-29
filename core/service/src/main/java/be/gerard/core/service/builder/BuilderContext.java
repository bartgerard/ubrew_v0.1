package be.gerard.core.service.builder;

import be.gerard.core.service.dao.ApplicationDao;
import be.gerard.core.service.dao.PrivilegeDao;
import be.gerard.core.service.dao.RoleDao;
import be.gerard.core.service.dao.UserDao;
import be.gerard.core.service.model.ApplicationRecord;
import be.gerard.core.service.model.PrivilegeRecord;
import be.gerard.core.service.model.RoleRecord;
import be.gerard.core.service.model.UserRecord;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * BuilderContext
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Component
public class BuilderContext {

//    private final Map<String, ApplicationRecord> applicationRecordMap = new HashMap<>();

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PrivilegeDao privilegeDao;

//    public void load() {
//        applicationRecordMap.clear();
//
//        for (ApplicationRecord applicationRecord : applicationDao.findAll()) {
//            applicationRecordMap.put(applicationRecord.getKey(), applicationRecord);
//        }
//    }

    // Get Or Create

//    public ApplicationRecord getOrCreateApplication(String key) {
//        Assert.hasText(key, "key is invalid [null]");
//        ApplicationRecord applicationRecord = applicationRecordMap.get(key);
//
//        if (applicationRecord == null) {
//            applicationRecord = new ApplicationRecord(key);
//            applicationRecordMap.put(applicationRecord.getKey(), applicationRecord);
//        }
//
//        return applicationRecord;
//    }


    public PasswordEncryptor getPasswordEncryptor() {
        return passwordEncryptor;
    }

    public ApplicationRecord getOrCreateApplication(String key) {
        Assert.hasText(key, "key is invalid [null]");

        ApplicationRecord applicationRecord = applicationDao.findByKey(key);

        if (applicationRecord == null) {
            applicationRecord = new ApplicationRecord(key);
        }

        return applicationRecord;
    }

    public UserRecord getOrCreateUser(String username) {
        Assert.hasText(username, "username is invalid [null]");

        UserRecord userRecord = userDao.findByUsername(username);

        if (userRecord == null) {
            userRecord = new UserRecord();
            userRecord.setUsername(username);
        }

        return userRecord;
    }

    public RoleRecord getOrCreateRole(String roleName) {
        Assert.hasText(roleName, "roleName is invalid [null]");

        RoleRecord roleRecord = roleDao.findByName(roleName);

        if (roleRecord == null) {
            roleRecord = new RoleRecord();
            roleRecord.setName(roleName);
        }

        return roleRecord;
    }

    public PrivilegeRecord getOrCreatePrivilege(String privilegeName) {
        Assert.hasText(privilegeName, "privilegeName is invalid [null]");

        PrivilegeRecord privilegeRecord = privilegeDao.findByName(privilegeName);

        if (privilegeRecord == null) {
            privilegeRecord = new PrivilegeRecord();
            privilegeRecord.setName(privilegeName);
        }

        return privilegeRecord;
    }

    // Build

    public ApplicationBuilder buildApplication(String key) {
        return new ApplicationBuilder(getOrCreateApplication(key), this);
    }

    public UserBuilder buildUser(String username) {
        return new UserBuilder(getOrCreateUser(username), this);
    }

    public RoleBuilder buildRole(String roleName) {
        return new RoleBuilder(getOrCreateRole(roleName), this);
    }

    public PrivilegeBuilder buildPrivilege(String privilegeName) {
        return new PrivilegeBuilder(getOrCreatePrivilege(privilegeName), this);
    }

    // Save

    public void save(ApplicationRecord applicationRecord) {
        applicationDao.save(applicationRecord);
    }

    public void save(UserRecord userRecord) {
        userDao.save(userRecord);
    }

    public void save(RoleRecord roleRecord) {
        roleDao.save(roleRecord);
    }

    public void save(PrivilegeRecord privilegeRecord) {
        privilegeDao.save(privilegeRecord);
    }

}
