package be.gerard.core.application.service.model;

import be.gerard.common.converter.annotation.Convertible;
import be.gerard.common.db.model.BaseRecord;
import be.gerard.core.application.interface_v1.model.Application;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
            name = "rel_application_property",
            joinColumns = @JoinColumn(name = "property_id")
    )
    @org.hibernate.annotations.ForeignKey(name = "fk_application_property", inverseName = "fk_property_application")
    private List<PropertyRecord> properties = new ArrayList<>();

    private ApplicationRecord() {
    }

    public ApplicationRecord(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public List<PropertyRecord> getProperties() {
        return properties;
    }

    public void addProperty(PropertyRecord property) {
        Assert.notNull(property, String.format("property is invalid [null]"));
        Assert.isTrue(this.properties.add(property), String.format("property can not be added to application.properties [key=%s]", property.getKey()));
    }

    public void removeProperty(PropertyRecord property) {
        Assert.notNull(property, String.format("property is invalid [null]"));
        Assert.isTrue(this.properties.remove(property), String.format("property can not be removed from application.properties [key=%s]", property.getKey()));
    }

}
