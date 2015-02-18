package be.gerard.translation.interface_v1.model;

import be.gerard.translation.interface_v1.enums.Language;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author bartgerard
 */
public class Translation implements Serializable {

    public Translation() {
    }

    public Translation(String application, String language, String key, String value) {
        this.application = application;
        this.language = language;
        this.key = key;
        this.value = value;
    }

    public Translation(String application, String language, String key, String value, String changedBy) {
        this(application, language, key, value);
        this.changedBy = changedBy;
    }

    public Translation(Long id, String application, String language, String key, String value, String changedBy) {
        this(application, language, key, value, changedBy);
        this.id = id;
    }

    private Long id;

    private String application;

    private String language;

    private String key;

    private String value;

    private String changedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.application);
        hash = 97 * hash + Objects.hashCode(this.language);
        hash = 97 * hash + Objects.hashCode(this.key);
        hash = 97 * hash + Objects.hashCode(this.value);
        hash = 97 * hash + Objects.hashCode(this.changedBy);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Translation other = (Translation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.application, other.application)) {
            return false;
        }
        if (!Objects.equals(this.language, other.language)) {
            return false;
        }
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return Objects.equals(this.changedBy, other.changedBy);
    }

    @Override
    public String toString() {
        return "Translation{" + "id=" + id + ", application=" + application + ", language=" + language + ", key=" + key + ", value=" + value + ", changedBy=" + changedBy + '}';
    }

}
