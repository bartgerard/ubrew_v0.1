package be.gerard.ubrew.core.service.model;

import be.gerard.common.db.model.BaseRecord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * ProductDefinition
 *
 * @author Bart Gerard
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_product_definition", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "ubrew_product_definition")
public class ProductDefinitionRecord extends BaseRecord {

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "relation_productdefinition_unit", joinColumns = @JoinColumn(name = "product_definition_id"), inverseJoinColumns = @JoinColumn(name = "unit_id"))
    private final List<UnitRecord> units = new ArrayList<>();

    public ProductDefinitionRecord() {
    }

    public List<UnitRecord> getUnits() {
        return Collections.unmodifiableList(units);
    }

    public void addUnit(UnitRecord unit) {
        this.units.add(unit);
    }

    public void removeUnit(UnitRecord unit) {
        this.units.remove(unit);
    }

    @Override
    public String toString() {
        return "ProductDefinitionRecord{" + "units=" + units + '}';
    }

}
