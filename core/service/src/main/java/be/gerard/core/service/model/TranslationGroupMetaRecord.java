package be.gerard.core.service.model;

import be.gerard.common.db.model.BaseRecord;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

/**
 * TranslationMetaGroupRecord
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_translation_group_meta", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "rel_application2translationgroup")
public class TranslationGroupMetaRecord extends BaseRecord {

    @Column(name = "priority")
    private Integer priority;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "translation_group_id", foreignKey = @ForeignKey(name = "fk_app2tg_translationgroup"))
    private TranslationGroupRecord group;

    TranslationGroupMetaRecord() {
    }

    public TranslationGroupMetaRecord(TranslationGroupRecord group) {
        this.group = group;
    }

    public Integer getPriority() {
        return priority;
    }

    public TranslationGroupRecord getGroup() {
        return group;
    }

    public void setGroup(TranslationGroupRecord group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TranslationGroupMetaRecord that = (TranslationGroupMetaRecord) o;
        return Objects.equals(priority, that.priority) &&
                Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), priority, group);
    }

}
