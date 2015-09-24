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
        ApplicationBuilder applicationBuilder = builderContext.buildApplication("core.web");

        applicationBuilder.translationGroup("common")
                .translation("ApplicationName", "uBrew", "nl", null, TranslationType.TITLE)
                .build();

        applicationBuilder.build();

        builderContext.saveAll();
    }

}
