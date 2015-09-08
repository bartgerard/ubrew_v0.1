package be.gerard.ubrew.core.interface_v1.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Quantity
 *
 * @author Bart Gerard
 */
public class Quantity implements Serializable {

    private Long id;

    private Integer amount;

    private Unit unit;

    public Quantity(Integer amount, Unit unit) {
        this(null, amount, unit);
    }

    public Quantity(Long id, Integer amount, Unit unit) {
        this.id = id;
        this.amount = amount;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.amount);
        hash = 83 * hash + Objects.hashCode(this.unit);
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
        final Quantity other = (Quantity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return Objects.equals(this.unit, other.unit);
    }

    @Override
    public String toString() {
        return "Quantity{" + "id=" + id + ", amount=" + amount + ", unit=" + unit + '}';
    }

}
