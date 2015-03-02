package be.gerard.core.service.model;

import be.gerard.common.converter.annotation.Convertible;
import be.gerard.common.db.model.BaseRecord;
import be.gerard.core.interface_v1.model.Property;

import javax.persistence.*;

/**
 * PropertyRecord
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-10 22:06
 */
@Convertible(defaultTargetType = Property.class)
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_property", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "core_property", uniqueConstraints = @UniqueConstraint(name = "uk_property_propKey", columnNames = {"prop_key"}))
public class PropertyRecord extends BaseRecord {

    @Column(name = "prop_key", nullable = false, updatable = false)
    private String key;

    @Column(name = "prop_group", nullable = false, updatable = false)
    private String group;

    @Column(name = "prop_value", nullable = false)
    private String value;

    private PropertyRecord() {
    }

    public PropertyRecord(String key, String group, String value) {
        this.key = key;
        this.group = group;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getGroup() {
        return group;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
