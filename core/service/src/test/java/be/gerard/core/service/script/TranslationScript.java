package be.gerard.core.service.script;

import be.gerard.core.interface_v1.enums.TranslationType;
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
        builderContext.buildApplication("core.web")
                .translationGroup("common")
                .and()
                .build().save();

        builderContext.buildApplication("ubrew.web")
                .translationGroup("common")
                .and()
                .translationGroup("ubrew")
                .translation("ApplicationName", "uBrew", "nl", null, TranslationType.TITLE)
                .translation("Home", "Thuis", "nl", null, TranslationType.TITLE)
                .translation("About", "Over", "nl", null, TranslationType.TITLE)
                .and()
                .build().save();
    }

}
