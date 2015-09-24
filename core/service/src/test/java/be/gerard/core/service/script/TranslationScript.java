package be.gerard.core.service.script;

import be.gerard.core.interface_v1.enums.TranslationType;
import be.gerard.core.service.builder.ApplicationBuilder;
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
        ApplicationBuilder core = builderContext.buildApplication("core.web");
        core.translationGroup("common");
        core.build().save();

        ApplicationBuilder ubrew = builderContext.buildApplication("ubrew.web");
        ubrew.translationGroup("common");
        ubrew.translationGroup("ubrew")
                .translation("ApplicationName", "uBrew", "nl", null, TranslationType.TITLE)
                .translation("Home", "Thuis", "nl", null, TranslationType.TITLE)
                .translation("About", "Over", "nl", null, TranslationType.TITLE)
                .build();
        ubrew.build().save();
    }

}
