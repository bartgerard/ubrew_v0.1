package be.gerard.ubrew.core.service.model;

import be.gerard.common.db.model.BaseRecord;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * UnitRecord
 *
 * @author Bart Gerard
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_unit", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "ubrew_unit")
public class UnitRecord extends BaseRecord {

    @Column(name = "label", nullable = false, unique = true, updatable = false)
    private String label;

    public UnitRecord() {
    }

    public UnitRecord(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return String.format("UnitRecord{label=%s}", label);
    }

}
