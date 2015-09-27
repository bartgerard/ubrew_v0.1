package be.gerard.core.service.script;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * AllScripts
 *
 * @author bartgerard
 * @version v0.0.1
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationScript.class,
        RoleScript.class,
        TranslationScript.class
})
public class AllScripts {
}
