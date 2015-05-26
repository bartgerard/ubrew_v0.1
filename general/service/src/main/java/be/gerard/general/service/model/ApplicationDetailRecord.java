package be.gerard.general.service.model;

import be.gerard.core.interface_v1.db.model.BaseRecord;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.NaturalId;

/**
 * ApplicationDetailRecord
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_application", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "general_application_detail", uniqueConstraints = @UniqueConstraint(name = "uk_application_key", columnNames = "app_key"))
public class ApplicationDetailRecord extends BaseRecord {

    @NaturalId
    @Column(name = "app_key", nullable = false)
    private String key;

    @Column(name = "app_name", nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rel_application_properties",
            foreignKey = @ForeignKey(name = "fk_property_application"),
            joinColumns = @JoinColumn(name = "application_id")
    )
    @MapKeyColumn(name = "propertyKey")
    @Column(name = "propertyValue")
    private final Map<String, String> properties = new HashMap<>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

}
