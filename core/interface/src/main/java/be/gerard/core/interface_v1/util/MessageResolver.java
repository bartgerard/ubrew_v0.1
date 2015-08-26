package be.gerard.core.interface_v1.util;

import be.gerard.core.interface_v1.TranslationService;
import be.gerard.core.interface_v1.enums.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * MessageResolver
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Component
public class MessageResolver implements Serializable {

    @Value("${be.gerard.core.application.name}")
    private String applicationName;

    @Value("${be.gerard.core.application.language.default}")
    private String defaultLanguage;

    private static final String KEY_NOT_FOUND = "Translation not found for key: %s";

    @Autowired
    private TranslationService translationService;

    public String get(String language, String key) {
        Assert.notNull(language, "language not valid [null].");
        Assert.notNull(key, "key not valid [null].");

        String result = translationService.get(applicationName, "common", Language.get(language), key);

        if (result == null && !defaultLanguage.equals(language)) {
            result = translationService.get(applicationName, "common", Language.get(defaultLanguage), key);
        }

        return result != null ? result : String.format(KEY_NOT_FOUND, key);
    }

}
