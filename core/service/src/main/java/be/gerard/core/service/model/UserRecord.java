package be.gerard.core.service.model;

import be.gerard.common.converter.annotation.Convertible;
import be.gerard.common.db.converter.LocalDatePersistenceConverter;
import be.gerard.common.db.model.BaseRecord;
import be.gerard.core.interface_v1.model.User;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * UserRecord
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Convertible(defaultTargetType = User.class)
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_user", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "core_user", uniqueConstraints = @UniqueConstraint(name = "uk_user_username", columnNames = {"username"}))
public class UserRecord extends BaseRecord {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Convert(converter = LocalDatePersistenceConverter.class)
    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;

    //@ForeignKey(name = "fk_user_email")
    @ElementCollection
    @CollectionTable(name = "rel_user_email", joinColumns = @JoinColumn(name = "user_id"))
    private final List<String> emails = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rel_user2role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    //@ForeignKey(name = "fk_u2r_user", inverseName = "fk_u2r_role")
    private final Set<RoleRecord> roles = new HashSet<>();

    public UserRecord() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return password;
    }

    public void setEncryptedPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getEmails() {
        return Collections.unmodifiableList(emails);
    }

    public void addEmail(String email) {
        this.emails.add(email);
    }

    public void removeEmail(String email) {
        this.emails.remove(email);
    }

    public Set<RoleRecord> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "UserRecord{" + "username=" + username + ", enabled=" + enabled + '}';
    }

}
