package be.gerard.core.service.builder;

import be.gerard.core.service.dao.ApplicationDao;
import be.gerard.core.service.dao.UserDao;
import be.gerard.core.service.model.ApplicationRecord;
import be.gerard.core.service.model.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

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

    private final Map<String, ApplicationRecord> applicationRecordMap = new HashMap<>();

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationDao applicationDao;

    public void load() {
        applicationRecordMap.clear();

        for (ApplicationRecord applicationRecord : applicationDao.findAll()) {
            applicationRecordMap.put(applicationRecord.getKey(), applicationRecord);
        }
    }

    // Get Or Create

    public ApplicationRecord getOrCreateApplication(String key) {
        Assert.hasText(key, "key is invalid [null]");
        ApplicationRecord applicationRecord = applicationRecordMap.get(key);

        if (applicationRecord == null) {
            applicationRecord = new ApplicationRecord(key);
            applicationRecordMap.put(applicationRecord.getKey(), applicationRecord);
        }

        return applicationRecord;
    }

    public UserRecord getOrCreateUser(String username) {
        UserRecord userRecord = userDao.findByUsername(username);

        if (userRecord == null) {
            userRecord = new UserRecord();
            userRecord.setUsername(username);
        }

        return userRecord;
    }

    // Build

    public ApplicationBuilder buildApplication(String key) {
        return new ApplicationBuilder(getOrCreateApplication(key), this);
    }

    public UserBuilder buildUser(String username) {
        return new UserBuilder(getOrCreateUser(username), this);
    }

    // Save

    public void save(ApplicationRecord applicationRecord) {
        applicationDao.save(applicationRecord);
    }

    public void save(UserRecord userRecord) {
        userDao.saveAndFlush(userRecord);
    }

}
