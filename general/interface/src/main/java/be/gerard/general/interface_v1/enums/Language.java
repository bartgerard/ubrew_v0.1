package be.gerard.general.interface_v1.enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author bartgerard
 */
public enum Language implements Serializable {

    NL("nl", new Locale("nl", "BE")),
    FR("fr", new Locale("fr", "BE")),
    EN("en", Locale.ENGLISH),
    UNDEFINED("xx", Locale.ENGLISH, false);

    private final String code;

    private final Locale locale;

    private boolean active;

    private Language(final String code, final Locale locale) {
        this(code, locale, true);
    }

    private Language(final String code, final Locale locale, final boolean active) {
        this.code = code;
        this.locale = locale;
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public Locale getLocale() {
        return locale;
    }

    public boolean isActive() {
        return active;
    }

    public static Language get(final String code) {
        if (code == null) {
            throw new IllegalArgumentException("code not valid [null].");
        }

        for (Language language : getAll()) {
            if (Objects.equals(language.getCode(), code)) {
                return language;
            }
        }
        
        return null;
    }

    public static Language get(final Locale locale) {
        if (locale == null) {
            return get(Locale.getDefault().getLanguage());
        } else {
            return get(locale.getLanguage());
        }
    }

    public static Language[] getAll() {
        return values();
    }

    public static Language[] getAllActive() {
        List<Language> languages = new ArrayList<>();
        
        for (Language language : getAll()) {
            if (language.isActive()) {
                languages.add(language);
            }
        }
        
        Language[] result = new Language[languages.size()];
        
        return languages.toArray(result);
    }
    
    public String key() {
        return String.format("%s_%s", getClass().getSimpleName(), getCode());
    }
    
}
