package be.gerard.core.service;

import be.gerard.core.service.dao.ApplicationDaoTest;
import be.gerard.core.service.dao.UserDaoTest;
import be.gerard.core.service.schema.CoreAuthenticationSchemaExportTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * CoreAuthenticationServiceTest
 *
 * @author bartgerard
 * @version 0.0.1
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CoreAuthenticationSchemaExportTest.class,
        UserDaoTest.class,
        ApplicationDaoTest.class,
        UserServiceTest.class,
        UserServiceTest.class,
        ApplicationServiceTest.class,
        TranslationServiceTest.class
})
public class CoreServiceTestSuite {

}
