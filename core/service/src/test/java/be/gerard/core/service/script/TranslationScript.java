package be.gerard.core.service.script;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

/**
 * TranslationScript
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class TranslationScript extends CommonScript {

    @Test
    @Rollback(false)
    public void initTranslations() {
        //builderContext.b
    }

}
