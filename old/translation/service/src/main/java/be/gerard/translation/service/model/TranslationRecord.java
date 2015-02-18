package be.gerard.translation.service.model;

import be.gerard.translation.interface_v1.enums.Language;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author bartgerard
 */
@Entity
@Table(name = "GENERAL_TRANSLATION", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"translationkey", "translationlanguage", "application"})})
public class TranslationRecord implements Serializable {

    public TranslationRecord() {
    }

    public TranslationRecord(String application, String language, String key, String value, String changedBy) {
        this.application = application;
        this.language = language;
        this.key = key;
        this.value = value;
        this.changedBy = changedBy;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "translation_generator")
    @SequenceGenerator(name = "translation_generator", sequenceName = "translation_sequence")
    private Long id;

    @Column(name = "application", nullable = false)
    private String application;

    @Column(name = "translationlanguage", nullable = false)
    private String language;

    @Column(name = "translationkey", nullable = false)
    private String key;

    @Column(name = "translationvalue", nullable = false)
    private String value;

    @Column(name = "changedBy", nullable = false)
    private String changedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.application);
        hash = 29 * hash + Objects.hashCode(this.language);
        hash = 29 * hash + Objects.hashCode(this.key);
        hash = 29 * hash + Objects.hashCode(this.value);
        hash = 29 * hash + Objects.hashCode(this.changedBy);
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
        final TranslationRecord other = (TranslationRecord) obj;
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
        return "TranslationRecord{" + "id=" + id + ", application=" + application + ", language=" + language + ", key=" + key + ", value=" + value + ", changedBy=" + changedBy + '}';
    }

}
