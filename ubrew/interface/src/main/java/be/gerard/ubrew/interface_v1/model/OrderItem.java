package be.gerard.ubrew.interface_v1.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * OrderItem
 *
 * @author Bart Gerard
 */
public class OrderItem implements Serializable {

    private Long id;

    private Product product;

    private Quantity quantity;

    public OrderItem(Long id, Product product, Quantity quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.product);
        hash = 37 * hash + Objects.hashCode(this.quantity);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderItem other = (OrderItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return Objects.equals(this.quantity, other.quantity);
    }

    @Override
    public String toString() {
        return "OrderItem{" + "id=" + id + ", product=" + product + ", quantity=" + quantity + '}';
    }

}
