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
                .translation("ApplicationName", "nl", "common").value("uBrew").type(TranslationType.TITLE)
                .and().translation("Home", "nl", "common").value("Home").type(TranslationType.TITLE)
                .and().translation("About", "nl", "common").value("Over").type(TranslationType.TITLE)
                .and().translation("ClickAndBrew", "nl", "common").value("Click and Brew").type(TranslationType.TITLE)
                .and().translation("MyBrew", "nl", "common").value("My Brew").type(TranslationType.TITLE)
                .and().translation("Profile", "nl", "common").value("Profile").type(TranslationType.TITLE)
                .and().translation("Email", "nl", "common").value("Email").type(TranslationType.FIELD)
                .and().translation("Password", "nl", "common").value("Password").type(TranslationType.FIELD)
                .and().translation("SignIn", "nl", "common").value("Log in").type(TranslationType.ACTION)
                .and().translation("Logout", "nl", "common").value("Logout").type(TranslationType.ACTION)
                .and()
                .build().save();
    }

}
