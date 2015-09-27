package be.gerard.core.service.model;

import be.gerard.common.db.model.BaseRecord;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * TranslationGroupRecord
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@SequenceGenerator(
        name = BaseRecord.SEQUENCE_GENERATOR,
        sequenceName = "s_translation_group",
        allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE
)
@Table(
        name = "core_translation_group",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_translation_group_key", columnNames = {"translation_group_key"})
        }
)
public class TranslationGroupRecord extends BaseRecord implements Keyable {

    @Column(name = "translation_group_key", nullable = false, updatable = false)
    private String key;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "translation_group_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_tg2t_translationgroup")
    )
    private final Set<TranslationRecord> translations = new HashSet<>();

    public TranslationGroupRecord() {
    }

    public TranslationGroupRecord(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Set<TranslationRecord> getTranslations() {
        return translations;
    }

    public TranslationRecord findByKeyAndLanguage(final String key, final String language) {
        for (TranslationRecord translationRecord : translations) {
            if (Objects.equals(translationRecord.getKey(), key) && Objects.equals(translationRecord.getLanguage(), language)) {
                return translationRecord;
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TranslationGroupRecord that = (TranslationGroupRecord) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(translations, that.translations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, translations);
    }

}
