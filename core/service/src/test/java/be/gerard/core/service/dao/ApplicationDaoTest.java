package be.gerard.core.service.dao;

import be.gerard.core.interface_v1.enums.PropertyType;
import be.gerard.core.service.BaseTest;
import be.gerard.core.service.model.ApplicationRecord;
import be.gerard.core.service.model.PropertyGroupRecord;
import be.gerard.core.service.model.PropertyRecord;
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
        PropertyGroupRecord propertyGroupRecord = new PropertyGroupRecord("PG-13");
        propertyGroupRecord.getProperties().add(new PropertyRecord(PROP1_KEY, PROP1_VALUE, PropertyType.URL));

        ApplicationRecord applicationRecord = new ApplicationRecord(APP_KEY);
        applicationRecord.getPropertyGroups().add(propertyGroupRecord);

        ApplicationRecord applicationRecordAfterSave = applicationDao.saveAndFlush(applicationRecord);

        ApplicationRecord applicationRecordAfterFindOne = applicationDao.findOne(applicationRecordAfterSave.getId());
        Assert.assertNotNull("applicationDao.findOne did return null", applicationRecordAfterFindOne);

        ApplicationRecord applicationRecordAfterFindByKey = applicationDao.findByKey(applicationRecord.getKey());
        Assert.assertNotNull("applicationDao.findByKey did return null", applicationRecordAfterFindByKey);

        Assert.assertFalse("application.properties is empty", applicationRecordAfterFindByKey.findProperties().isEmpty());
    }

}
