package be.gerard.core.user.interface_v1.model;

import be.gerard.common.to.BaseTo;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author bartgerard
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user")
public class User extends BaseTo {

    public User() {
    }

    public User(String username, String firstname, String lastname, LocalDate birthDate) {
        this(null, username, firstname, lastname, birthDate);
    }

    public User(Long id, String username, String firstname, String lastname, LocalDate birthDate) {
        super(id);
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
    }

    @XmlElement(required = true)
    private String username;

    @XmlElement(required = true)
    private String firstname;

    @XmlElement(required = true)
    private String lastname;

    @XmlElement(required = true)
    private LocalDate birthDate;

    @XmlElement(required = true)
    private final Map<String, String> properties = new HashMap<>();

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

}
