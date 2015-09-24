package be.gerard.core.service.builder;

import be.gerard.core.service.dao.ApplicationDao;
import be.gerard.core.service.dao.PrivilegeDao;
import be.gerard.core.service.dao.PropertyGroupDao;
import be.gerard.core.service.dao.RoleDao;
import be.gerard.core.service.dao.TranslationGroupDao;
import be.gerard.core.service.dao.UserDao;
import be.gerard.core.service.model.ApplicationRecord;
import be.gerard.core.service.model.PrivilegeRecord;
import be.gerard.core.service.model.PropertyGroupRecord;
import be.gerard.core.service.model.RoleRecord;
import be.gerard.core.service.model.TranslationGroupRecord;
import be.gerard.core.service.model.UserRecord;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * BuilderContext
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Component
public class BuilderContext {

    // MAPS

    private final Map<String, ApplicationRecord> applications = new HashMap<>();

    private final Map<String, PropertyGroupRecord> propertyGroups = new HashMap<>();

    private final Map<String, TranslationGroupRecord> translationGroups = new HashMap<>();

    private final Map<String, UserRecord> users = new HashMap<>();

    private final Map<String, RoleRecord> roles = new HashMap<>();

    private final Map<String, PrivilegeRecord> privileges = new HashMap<>();

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private PropertyGroupDao propertyGroupDao;

    @Autowired
    private TranslationGroupDao translationGroupDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PrivilegeDao privilegeDao;

    @PostConstruct
    public void load() {
        // Applications
        applications.clear();

        for (ApplicationRecord applicationRecord : applicationDao.findAll()) {
            applications.put(applicationRecord.getKey(), applicationRecord);
        }

        // PropertyGroups
        propertyGroups.clear();

        for (PropertyGroupRecord propertyGroupRecord : propertyGroupDao.findAll()) {
            propertyGroups.put(propertyGroupRecord.getKey(), propertyGroupRecord);
        }

        // TranslationGroups
        translationGroups.clear();

        for (TranslationGroupRecord translationGroupRecord : translationGroupDao.findAll()) {
            translationGroups.put(translationGroupRecord.getKey(), translationGroupRecord);
        }

        // Users
        users.clear();

        for (UserRecord userRecord : userDao.findAll()) {
            users.put(userRecord.getUsername(), userRecord);
        }

        // Roles
        roles.clear();

        for (RoleRecord roleRecord : roleDao.findAll()) {
            roles.put(roleRecord.getName(), roleRecord);
        }

        // Privileges
        privileges.clear();

        for (PrivilegeRecord privilegeRecord : privilegeDao.findAll()) {
            privileges.put(privilegeRecord.getName(), privilegeRecord);
        }
    }

    // Get Or Create


    public PasswordEncryptor getPasswordEncryptor() {
        return passwordEncryptor;
    }

    public ApplicationRecord getOrCreateApplication(String key) {
        Assert.hasText(key, "key is invalid [null]");

        ApplicationRecord applicationRecord = applications.get(key);

        if (applicationRecord == null) {
            applicationRecord = new ApplicationRecord(key);
            applications.put(key, applicationRecord);
        }

        return applicationRecord;
    }

    public PropertyGroupRecord getOrProperyGroup(String key) {
        Assert.hasText(key, "key is invalid [null]");

        PropertyGroupRecord propertyGroupRecord = propertyGroups.get(key);

        if (propertyGroupRecord == null) {
            propertyGroupRecord = new PropertyGroupRecord(key);
            propertyGroups.put(key, propertyGroupRecord);
        }

        return propertyGroupRecord;
    }

    public TranslationGroupRecord getOrCreateTranslationGroup(String key) {
        Assert.hasText(key, "key is invalid [null]");

        TranslationGroupRecord translationGroupRecord = translationGroups.get(key);

        if (translationGroupRecord == null) {
            translationGroupRecord = new TranslationGroupRecord(key);
            translationGroups.put(key, translationGroupRecord);
        }

        return translationGroupRecord;
    }

    public UserRecord getOrCreateUser(String username) {
        Assert.hasText(username, "username is invalid [null]");

        UserRecord userRecord = users.get(username);

        if (userRecord == null) {
            userRecord = new UserRecord();
            userRecord.setUsername(username);
            users.put(username, userRecord);
        }

        return userRecord;
    }

    public RoleRecord getOrCreateRole(String roleName) {
        Assert.hasText(roleName, "roleName is invalid [null]");

        RoleRecord roleRecord = roles.get(roleName);

        if (roleRecord == null) {
            roleRecord = new RoleRecord();
            roleRecord.setName(roleName);
            roles.put(roleName, roleRecord);
        }

        return roleRecord;
    }

    public PrivilegeRecord getOrCreatePrivilege(String privilegeName) {
        Assert.hasText(privilegeName, "privilegeName is invalid [null]");

        PrivilegeRecord privilegeRecord = privileges.get(privilegeName);

        if (privilegeRecord == null) {
            privilegeRecord = new PrivilegeRecord();
            privilegeRecord.setName(privilegeName);
            privileges.put(privilegeName, privilegeRecord);
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

    public void saveAll() {
        privilegeDao.save(privileges.values());
        roleDao.save(roles.values());
        userDao.save(users.values());
        applicationDao.save(applications.values());
    }

}
