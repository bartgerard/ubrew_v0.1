package be.gerard.core.service.dao;

import be.gerard.core.service.BaseTest;
import be.gerard.core.service.model.ApplicationInstanceRecord;
import be.gerard.core.service.model.ApplicationRecord;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserDaoTest
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class ApplicationInstanceDaoTest extends BaseTest {
    
    @Autowired
    private ApplicationInstanceDao applicationInstanceDao;

    @Test
    public void test() {
        ApplicationRecord applicationRecord = new ApplicationRecord(APP_KEY);

        ApplicationInstanceRecord applicationInstanceRecord = new ApplicationInstanceRecord(applicationRecord, REFERENCE, PASSWORD);
        applicationInstanceRecord.addIp(IP);
        applicationInstanceRecord.addMac(MAC);
        applicationInstanceDao.saveAndFlush(applicationInstanceRecord);

        ApplicationInstanceRecord applicationInstanceRecord1 = applicationInstanceDao.findByApplicationAndReference(applicationRecord, REFERENCE);
        Assert.assertTrue("application.ips does not contain ip", applicationInstanceRecord1.isIpAllowed(IP));
        Assert.assertTrue("application.macs does not contain mac", applicationInstanceRecord1.isMacAllowed(MAC));


        ApplicationInstanceRecord applicationInstanceRecord2 = applicationInstanceDao.findByApplicationAndReference(applicationRecord.getKey(), REFERENCE);
        Assert.assertNotNull("application not found", applicationInstanceRecord2);
    }

}
