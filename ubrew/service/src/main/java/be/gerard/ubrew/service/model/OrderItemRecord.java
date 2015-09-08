package be.gerard.ubrew.service.model;

import be.gerard.common.db.model.BaseRecord;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * OrderItem
 *
 * @author Bart Gerard
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_order_item", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "ubrew_order_item")
public class OrderItemRecord extends BaseRecord {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductRecord product;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "quantity", nullable = false)
    private QuantityRecord quantity;

    public OrderItemRecord() {
    }

    public OrderItemRecord(Long id, ProductRecord product, QuantityRecord quantity) {
        super(id);
        this.product = product;
        this.quantity = quantity;
    }

    public ProductRecord getProduct() {
        return product;
    }

    public void setProduct(ProductRecord product) {
        this.product = product;
    }

    public QuantityRecord getQuantity() {
        return quantity;
    }

    public void setQuantity(QuantityRecord quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemRecord{" + "product=" + product + ", quantity=" + quantity + '}';
    }

}
