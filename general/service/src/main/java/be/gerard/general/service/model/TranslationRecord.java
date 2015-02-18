package be.gerard.general.service.model;

import be.gerard.common.db.model.BaseRecord;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author bartgerard
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_translation", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "general_translation", uniqueConstraints = {
    @UniqueConstraint(name = "uk_translation_key_lan_app", columnNames = {"translationkey", "translationlanguage", "application"})})
public class TranslationRecord extends BaseRecord {

    public TranslationRecord() {
    }

    public TranslationRecord(String application, String language, String key, String value) {
        this.application = application;
        this.language = language;
        this.key = key;
        this.value = value;
    }

    @Column(name = "application", nullable = false)
    private String application;

    @Column(name = "translationlanguage", nullable = false)
    private String language;

    @Column(name = "translationkey", nullable = false)
    private String key;

    @Column(name = "translationvalue", nullable = false)
    private String value;

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

}
