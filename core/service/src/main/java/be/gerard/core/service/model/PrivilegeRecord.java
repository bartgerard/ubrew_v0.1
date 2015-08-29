package be.gerard.core.service.model;

import be.gerard.common.db.model.BaseRecord;

import javax.persistence.*;

/**
 * PrivilegeRecord
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_privilege", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "core_privilege", uniqueConstraints = @UniqueConstraint(name = "uk_privilege_name", columnNames = {"name"}))
public class PrivilegeRecord extends BaseRecord {

    @Column(name = "name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
