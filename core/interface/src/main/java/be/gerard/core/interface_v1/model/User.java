package be.gerard.core.interface_v1.model;

import be.gerard.common.to.BaseTo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author bartgerard
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user")
public class User extends BaseTo {

    @XmlElement(required = true)
    private UUID cuid;

    @XmlElement(required = true)
    private String username;

    @XmlElement(required = true)
    private String firstname;

    @XmlElement(required = true)
    private String lastname;

    @XmlElement(required = true)
    private LocalDate birthDate;

    @XmlElement(required = true)
    private List<String> emails = new ArrayList<>();

    @XmlElement(required = true)
    private final Map<String, String> properties = new HashMap<>();

    @XmlElement(required = true)
    private final Set<Role> roles = new HashSet<>();

    public User() {
    }

    public UUID getCuid() {
        return cuid;
    }

    public void setCuid(UUID cuid) {
        this.cuid = cuid;
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return emails;
    }

    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    public String get(final String key) {
        return properties.get(key);
    }

    public void put(final String key, final String value) {
        properties.put(key, value);
    }

    public void remove(final String key) {
        properties.remove(key);
    }

    public Set<Role> getRoles() {
        return roles;
    }

}
