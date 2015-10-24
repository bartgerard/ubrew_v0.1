package be.gerard.game.service.model;

import be.gerard.common.db.model.BaseRecord;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Product
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_product", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "bgc_product")
public class ProductRecord extends BaseRecord {

    @OneToOne
    @JoinColumn(name = "prd_def_id", foreignKey = @ForeignKey(name = "fk_prd2pdd"))
    private ProductDefinitionRecord productDefinition;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @OneToMany(orphanRemoval = true)
    @JoinTable(
            name = "rel_product2price",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "price_id"),
            foreignKey = @ForeignKey(name = "fk_product2price"),
            inverseForeignKey = @ForeignKey(name = "fk_price2product")
    )
    private final Set<PriceRecord> prices = new HashSet<>();

    public ProductRecord() {
    }

    public ProductRecord(ProductDefinitionRecord productDefinition) {
        this(productDefinition, 1);
    }

    public ProductRecord(ProductDefinitionRecord productDefinition, int quantity, PriceRecord... prices) {
        this.productDefinition = productDefinition;
        this.quantity = quantity;
        Collections.addAll(this.prices, prices);
    }

    public ProductDefinitionRecord getProductDefinition() {
        return productDefinition;
    }

    public void setProductDefinition(ProductDefinitionRecord productDefinition) {
        this.productDefinition = productDefinition;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<PriceRecord> getPrices() {
        return prices;
    }

}
