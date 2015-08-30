package be.gerard.core.service.model;

import be.gerard.common.converter.annotation.Convertible;
import be.gerard.common.db.model.BaseRecord;
import be.gerard.core.interface_v1.model.Application;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
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
public class ApplicationRecord extends BaseRecord {

    @Column(name = "app_key", nullable = false, updatable = false)
    private String key;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "rel_application2property",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
        @org.hibernate.annotations.ForeignKey(name = "fk_a2p_property", inverseName = "fk_a2p_application")
    private Set<PropertyRecord> properties = new HashSet<>();

    private ApplicationRecord() {
    }

    public ApplicationRecord(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public Set<PropertyRecord> getProperties() {
        return properties;
    }

    public PropertyRecord findProperty(String key) {
        for (PropertyRecord property : properties) {
            if (Objects.equals(property.getKey(), key)) {
                return property;
            }
        }

        return null;
    }

    public void addProperty(PropertyRecord property) {
        Assert.notNull(property, "property is invalid [null]");
        Assert.isTrue(this.properties.add(property), String.format("property can not be added to application.properties [key=%s]", property.getKey()));
    }

    public void removeProperty(PropertyRecord property) {
        Assert.notNull(property, "property is invalid [null]");
        Assert.isTrue(this.properties.remove(property), String.format("property can not be removed from application.properties [key=%s]", property.getKey()));
    }

    public void clearProperties() {
        this.properties.clear();
    }

}
