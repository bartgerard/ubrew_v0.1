package be.gerard.core.service.model;

import be.gerard.common.converter.annotation.Convertible;
import be.gerard.common.db.converter.LocalDatePersistenceConverter;
import be.gerard.core.interface_v1.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * UserRecord
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Convertible(defaultTargetType = User.class)
@Entity
@Table(name = "core_user")
public class UserRecord implements Serializable {

    // Core User Id --> CUID
    @Id
    @Column(name = "cuid", nullable = false)
    private UUID cuid = UUID.randomUUID();

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

    @ElementCollection
    @CollectionTable(name = "rel_user_email", joinColumns = @JoinColumn(name = "user_cuid"))
    private final List<String> emails = new ArrayList<>();

    public UserRecord() {
    }

    public UUID getCuid() {
        return cuid;
    }

    public void setCuid(UUID cuid) {
        this.cuid = cuid;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.username);
        hash = 67 * hash + Objects.hashCode(this.password);
        hash = 67 * hash + (this.enabled ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserRecord other = (UserRecord) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return this.enabled == other.enabled;
    }

    @Override
    public String toString() {
        return "UserRecord{" + "username=" + username + ", enabled=" + enabled + '}';
    }

}
