package be.gerard.ubrew.core.service.model;

import be.gerard.common.db.model.BaseRecord;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Quantity
 *
 * @author Bart Gerard
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_quantity", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "ubrew_quantity")
public class QuantityRecord extends BaseRecord {

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "unit", nullable = false)
    private UnitRecord unit;

    public QuantityRecord() {
    }

    public QuantityRecord(Integer amount, UnitRecord unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public UnitRecord getUnit() {
        return unit;
    }

    public void setUnit(UnitRecord unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "QuantityRecord{" + "amount=" + amount + ", unit=" + unit + '}';
    }

}
