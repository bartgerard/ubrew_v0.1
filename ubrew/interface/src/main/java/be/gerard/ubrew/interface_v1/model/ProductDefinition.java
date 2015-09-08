package be.gerard.ubrew.interface_v1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * ProductDefinition
 *
 * @author Bart Gerard
 */
public class ProductDefinition implements Serializable {

    private Long id;

    private final List<Unit> units = new ArrayList<>();

    public ProductDefinition() {
    }

    public ProductDefinition(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Unit> getUnits() {
        return Collections.unmodifiableList(units);
    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
    }

    public void removeUnit(Unit unit) {
        this.units.remove(unit);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.units);
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
        final ProductDefinition other = (ProductDefinition) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.units, other.units);
    }

    @Override
    public String toString() {
        return "ProductDefinition{" + "id=" + id + ", units=" + units + '}';
    }

}
