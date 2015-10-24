package be.gerard.game.service.model;

import be.gerard.common.db.model.BaseRecord;
import be.gerard.game.interface_v1.model.CurrencyCode;
import be.gerard.game.interface_v1.model.PriceType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Price
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_price", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "bgc_price")
public class PriceRecord extends BaseRecord {

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 20)
    private PriceType type;

    @Column(name = "value", precision = 2)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", length = 3)
    private CurrencyCode currency;

    public PriceRecord() {
    }

    public PriceRecord(PriceType type, BigDecimal value, CurrencyCode currency) {
        this.type = type;
        this.value = value;
        this.currency = currency;
    }

    public PriceType getType() {
        return type;
    }

    public void setType(PriceType type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public CurrencyCode getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyCode currency) {
        this.currency = currency;
    }

}
