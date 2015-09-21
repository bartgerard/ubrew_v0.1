package be.gerard.core.service.dao;

import be.gerard.core.interface_v1.enums.PropertyType;
import be.gerard.core.service.BaseTest;
import be.gerard.core.service.model.ApplicationRecord;
import be.gerard.core.service.model.PropertyGroupRecord;
import be.gerard.core.service.model.PropertyRecord;
import be.gerard.core.service.specs.PropertySpecs;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * PropertyDaoTest
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class PropertyDaoTest extends BaseTest {

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private PropertyDao propertyDao;

    @Test
    public void test() {
        PropertyGroupRecord pg1 = new PropertyGroupRecord("test1");
        pg1.getProperties().add(new PropertyRecord("a", PropertyType.URL, "a"));

        PropertyGroupRecord pg2 = new PropertyGroupRecord("test2");
        pg2.getProperties().add(new PropertyRecord("a", PropertyType.URL, "abis"));

        ApplicationRecord applicationRecord = new ApplicationRecord("app1");
        applicationRecord.getPropertyGroups().add(pg1);
        //applicationRecord.getPropertyGroups().add(pg2);

        ApplicationRecord a = applicationDao.saveAndFlush(applicationRecord);

        a.findProperties();

        System.out.println(Objects.toString(propertyDao.findAll(PropertySpecs.findByApplication("app1"))));
    }

}
