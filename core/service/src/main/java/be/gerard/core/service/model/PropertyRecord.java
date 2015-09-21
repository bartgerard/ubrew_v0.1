package be.gerard.core.service.model;

import be.gerard.common.converter.annotation.Convertible;
import be.gerard.common.db.model.BaseRecord;
import be.gerard.core.interface_v1.enums.PropertyType;
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
@Table(name = "core_property", uniqueConstraints = @UniqueConstraint(name = "uk_property_key", columnNames = {"property_key", "property_group_id"}))
public class PropertyRecord extends BaseRecord implements Keyable {

    @Column(name = "property_key", nullable = false, updatable = false)
    private String key;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PropertyType type;

    @Column(name = "value", nullable = false)
    private String value;

    public PropertyRecord() {
    }

    public PropertyRecord(String key) {
        this.key = key;
    }

    public PropertyRecord(String key, PropertyType type, String value) {
        this.key = key;
        this.type = type;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
