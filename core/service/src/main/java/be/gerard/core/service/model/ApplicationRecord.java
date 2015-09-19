package be.gerard.core.service.model;

import be.gerard.common.converter.annotation.Convertible;
import be.gerard.common.db.model.BaseRecord;
import be.gerard.core.interface_v1.model.Application;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * ApplicationRecord
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Convertible(defaultTargetType = Application.class)
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_application", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "core_application", uniqueConstraints = @UniqueConstraint(name = "uk_application_app_key", columnNames = {"app_key"}))
public class ApplicationRecord extends BaseRecord implements Keyable {

    @Column(name = "app_key", nullable = false, updatable = false)
    private String key;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "rel_application2propertygroup",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "property_group_id")
    )
    @org.hibernate.annotations.ForeignKey(name = "fk_a2pg_propertygroup", inverseName = "fk_a2pg_application")
    @OrderBy("priority")
    private final Set<PropertyGroupRecord> propertyGroups = new HashSet<>();

    public ApplicationRecord() {
    }

    public ApplicationRecord(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    public Set<PropertyGroupRecord> getPropertyGroups() {
        return propertyGroups;
    }

    public PropertyGroupRecord findPropertyGroup(final String key) {
        for (PropertyGroupRecord propertyGroupRecord : propertyGroups) {
            if (Objects.equals(propertyGroupRecord.getKey(), key)) {
                return propertyGroupRecord;
            }
        }

        return null;
    }

    /**
     * This method assumes that all property groups are propery ordered.
     *
     * @return
     */
    public Map<String, String> findProperties() {
        final Map<String, String> properties = new HashMap<>();

        for (PropertyGroupRecord propertyGroup : propertyGroups) {
            propertyGroup.getProperties().stream().filter(property -> !properties.containsKey(property.getKey())).forEach(property -> properties.put(property.getKey(), property.getValue()));
        }

        return properties;
    }

}
