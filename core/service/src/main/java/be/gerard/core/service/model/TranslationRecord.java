package be.gerard.core.service.model;

import be.gerard.common.converter.annotation.Convertible;
import be.gerard.common.db.model.BaseRecord;
import be.gerard.core.interface_v1.enums.TranslationType;
import be.gerard.core.interface_v1.model.Translation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

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

    @Column(name = "translation_key", nullable = false)
    private String key;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "translation_language", nullable = false, length = 2)
    private String language;

    @Column(name = "prefix", nullable = true)
    private String prefix;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private TranslationType type;

    public TranslationRecord() {
    }

    public TranslationRecord(String key, String value, String language, String prefix, TranslationType type) {
        this.key = key;
        this.value = value;
        this.language = language;
        this.prefix = prefix;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TranslationRecord that = (TranslationRecord) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(value, that.value) &&
                Objects.equals(language, that.language) &&
                Objects.equals(prefix, that.prefix) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, value, language, prefix, type);
    }

    @Override
    public String toString() {
        return "TranslationRecord{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", language='" + language + '\'' +
                ", prefix='" + prefix + '\'' +
                ", type=" + type +
                '}';
    }

}
