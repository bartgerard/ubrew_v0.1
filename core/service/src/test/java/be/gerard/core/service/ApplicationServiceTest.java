package be.gerard.core.service;

import be.gerard.core.interface_v1.ApplicationService;
import be.gerard.core.interface_v1.enums.PropertyType;
import be.gerard.core.interface_v1.model.Application;
import be.gerard.core.interface_v1.model.Property;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserServiceImplTest
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Transactional
public class ApplicationServiceTest extends BaseTest {

    @Autowired
    private ApplicationService applicationService;

    @Test
    public void test() {
        Application application = new Application(APP_KEY);
        application.getProperties().add(new Property(PROP1_KEY, PropertyType.URL, PROP1_VALUE));

        Application application1 = applicationService.save(application);

        Assert.assertNotNull(application1);
    }
    
}
