package be.gerard.general.util;

import be.gerard.general.interface_v1.TranslationService;
import be.gerard.general.interface_v1.enums.Language;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 *
 * @author bartgerard
 */
@Component
public class MessageResolver implements Serializable {

    @Value("${be.gerard.general.application.name}")
    private String applicationName;

    @Value("${be.gerard.general.application.language.default}")
    private String defaultLanguage;

    private final static String KEY_NOT_FOUND = "Translation not found for key: %s";

//    private final Map<String, Map<String, String>> translations = new HashMap<>();

    @Autowired
    private TranslationService translationService;

    /**
     * When the object is created the load method should be activated to load
     * all translations into the cache. If later on changes would be made in the
     * languageService this component requires, the load() method should be
     * called to renew all translations.
     */
//    @PostConstruct
//    public void load() {
//        this.translations.clear();
//
//        translationService.findAllSupportedLanguages().stream().forEach((language) -> {
//            translations.put(language, new HashMap<>());
//        });
//
//        Assert.hasText(applicationName, String.format("applicationName not valid [%s].", applicationName));
//        Assert.hasText(defaultLanguage, String.format("defaultLanguage not valid [%s].", defaultLanguage));
//
//        translationService.findAll(applicationName).stream().forEach((translation) -> {
//            this.translations.get(translation.getLanguage()).put(translation.getKey(), translation.getValue());
//        });
//    }

    /**
     * Return the translation for a given key and language. If the translation
     * doesn't exist in the given language, the value of the key in the default
     * language is returned.
     *
     * @param language The preferred language of the translation.
     * @param key The key for which a translation is required.
     * @return translation
     */
    public String get(final String language, final String key) {
        Assert.notNull(language, "language not valid [null].");
        Assert.notNull(key, "key not valid [null].");

//        if (this.translations.containsKey(language)) {
//            String result = this.translations.get(language).get(key);
//
//            if (result != null) {
//                return result;
//            } else if (!defaultLanguage.equals(language)) {
//                result = this.translations.get(defaultLanguage).get(key);
//            }
//
//            if (result != null) {
//                return result;
//            }
//        }
        
        String result = translationService.get(applicationName, Language.get(language), key);
        
        if (result== null && !defaultLanguage.equals(language)) {
            result = translationService.get(applicationName, Language.get(defaultLanguage), key);
        }
        
        return result != null ? result : String.format(KEY_NOT_FOUND, key);
    }

}
