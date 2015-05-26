package be.gerard.core.service.builder;

import be.gerard.core.service.dao.ApplicationDao;
import be.gerard.core.service.model.ApplicationRecord;
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
    private ApplicationDao applicationDao;

    public void load() {
        applicationRecordMap.clear();

        for (ApplicationRecord applicationRecord : applicationDao.findAll()) {
            applicationRecordMap.put(applicationRecord.getKey(), applicationRecord);
        }
    }

    public ApplicationRecord getOrCreateApplication(String key) {
        Assert.hasText(key, "key is invalid [null]");
        ApplicationRecord applicationRecord = applicationRecordMap.get(key);

        if (applicationRecord == null) {
            applicationRecord = new ApplicationRecord(key);
            applicationRecordMap.put(applicationRecord.getKey(), applicationRecord);
        }

        return applicationRecord;
    }

    public ApplicationBuilder buildApplication(String key) {
        return new ApplicationBuilder(getOrCreateApplication(key), this);
    }

    public void save(ApplicationRecord applicationRecord) {
        applicationDao.save(applicationRecord);
    }

}
