package be.gerard.core.service.model;

import be.gerard.common.converter.annotation.Convertible;
import be.gerard.common.db.model.BaseRecord;
import be.gerard.core.interface_v1.enums.TranslationType;
import be.gerard.core.interface_v1.model.Translation;

import javax.persistence.*;

/**
 * @author bartgerard
 */
@Convertible(defaultTargetType = Translation.class)
@Entity
@SequenceGenerator(
        name = BaseRecord.SEQUENCE_GENERATOR,
        sequenceName = "s_translation",
        allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE
)
@Table(
        name = "core_translation",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_translation_key_lan_app",
                        columnNames = {"prefix", "translation_key", "translation_language", "translation_group_id"}
                )
        }
)
public class TranslationRecord extends BaseRecord {

//    @Column(name = "app_key", nullable = false)
//    private String application;

    @Column(name = "translation_language", nullable = false)
    private String language;

    @Column(name = "prefix", nullable = false)
    private String prefix;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private TranslationType type;

    @Column(name = "translation_key", nullable = false)
    private String key;

    @Column(name = "value", nullable = false)
    private String value;

//    public String getApplication() {
//        return application;
//    }
//
//    public void setApplication(String application) {
//        this.application = application;
//    }

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
