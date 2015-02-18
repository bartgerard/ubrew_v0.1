package be.gerard.core.service.dao;

import be.gerard.core.application.service.dao.ApplicationDao;
import be.gerard.core.application.service.model.ApplicationRecord;
import be.gerard.core.application.service.model.PropertyRecord;
import be.gerard.core.service.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserDaoTest
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class ApplicationDaoTest extends BaseTest {

    @Autowired
    private ApplicationDao applicationDao;

    @Test
    public void test() {
        ApplicationRecord applicationRecord = new ApplicationRecord(APP_KEY);
        applicationRecord.addProperty(new PropertyRecord(PROP1_KEY, PROP1_GROUP, PROP1_VALUE));

        ApplicationRecord applicationRecordAfterSave = applicationDao.saveAndFlush(applicationRecord);

        ApplicationRecord applicationRecordAfterFindOne = applicationDao.findOne(applicationRecordAfterSave.getId());
        Assert.assertNotNull("applicationDao.findOne did return null", applicationRecordAfterFindOne);

        ApplicationRecord applicationRecordAfterFindByKey = applicationDao.findByKey(applicationRecord.getKey());
        Assert.assertNotNull("applicationDao.findByKey did return null", applicationRecordAfterFindByKey);

        Assert.assertFalse("application.properties is empty", applicationRecordAfterFindByKey.getProperties().isEmpty());
    }

}
