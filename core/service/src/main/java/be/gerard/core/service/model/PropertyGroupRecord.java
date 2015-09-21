package be.gerard.core.service.model;

import be.gerard.common.db.model.BaseRecord;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * PropertyGroupRecord
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_property_group", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "core_property_group", uniqueConstraints = @UniqueConstraint(name = "uk_propertygroup_key", columnNames = {"property_group_key"}))
public class PropertyGroupRecord extends BaseRecord implements Keyable {

    @Column(name = "property_group_key", nullable = false, updatable = false)
    private String key;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "property_group_id", nullable = false)
    //@org.hibernate.annotations.ForeignKey(name = "fk_pg2p_propertygroup")
    private final Set<PropertyRecord> properties = new HashSet<>();

    public PropertyGroupRecord() {
    }

    public PropertyGroupRecord(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Set<PropertyRecord> getProperties() {
        return properties;
    }

    public PropertyRecord findProperty(final String key) {
        for (PropertyRecord propertyRecord : properties) {
            if (Objects.equals(propertyRecord.getKey(), key)) {
                return propertyRecord;
            }
        }

        return null;
    }

}
