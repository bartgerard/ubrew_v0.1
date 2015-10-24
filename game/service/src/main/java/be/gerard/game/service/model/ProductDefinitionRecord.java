package be.gerard.game.service.model;

import be.gerard.common.db.model.BaseRecord;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * ProductDefinition
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_product_definition", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "bgc_product_definition")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING, length = 20)
public abstract class ProductDefinitionRecord extends BaseRecord {

    @Column(name = "prd_key")
    private String key;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "msrp_id")
    private PriceRecord msrp;

    public ProductDefinitionRecord() {
    }

    public ProductDefinitionRecord(String key, String name, PriceRecord msrp) {
        this.key = key;
        this.name = name;
        this.msrp = msrp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PriceRecord getMsrp() {
        return msrp;
    }

    public void setMsrp(PriceRecord msrp) {
        this.msrp = msrp;
    }

}
