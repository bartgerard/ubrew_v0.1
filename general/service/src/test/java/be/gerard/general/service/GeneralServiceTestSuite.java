package be.gerard.general.service;

import be.gerard.general.service.schema.SchemaTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author bartgerard
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({SchemaTest.class, UserTest.class, TranslationTest.class})
public class GeneralServiceTestSuite {

}
