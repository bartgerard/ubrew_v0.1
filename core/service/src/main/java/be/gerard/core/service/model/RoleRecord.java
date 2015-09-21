package be.gerard.core.service.model;

import be.gerard.common.converter.annotation.Convertible;
import be.gerard.common.db.model.BaseRecord;
import be.gerard.core.interface_v1.model.Role;

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
import java.util.Set;

/**
 * RoleRecord
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Convertible(defaultTargetType = Role.class)
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_role", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "core_role", uniqueConstraints = @UniqueConstraint(name = "uk_role_name", columnNames = {"name"}))
public class RoleRecord extends BaseRecord {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rel_role2privilege",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    //@ForeignKey(name = "fk_r2p_role", inverseName = "fk_r2p_privilege")
    private final Set<PrivilegeRecord> privileges = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PrivilegeRecord> getPrivileges() {
        return privileges;
    }

}
