package be.gerard.core.interface_v1.model;

import be.gerard.common.to.BaseTo;
import be.gerard.core.interface_v1.enums.TranslationType;

/**
 * @author bartgerard
 */
public class Translation extends BaseTo {

    public Translation() {
    }

    public Translation(
            String application,
            String language,
            String prefix,
            TranslationType type,
            String key,
            String value
    ) {
        this(application, language, prefix, type, key, value, null);
    }

    public Translation(
            String application,
            String language,
            String prefix,
            TranslationType type,
            String key,
            String value,
            String changedBy
    ) {
        this(null, application, language, prefix, type, key, value, changedBy);
    }

    public Translation(
            Long id,
            String application,
            String language,
            String prefix,
            TranslationType type,
            String key,
            String value,
            String changedBy
    ) {
        super(id);
        this.application = application;
        this.language = language;
        this.prefix = prefix;
        this.type = type;
        this.key = key;
        this.value = value;
        this.changedBy = changedBy;
    }

    private String application;

    private String language;

    private String prefix;

    private TranslationType type;

    private String key;

    private String value;

    private String changedBy;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public TranslationType getType() {
        return type;
    }

    public void setType(TranslationType type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

}
