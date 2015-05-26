package be.gerard.general.interface_v1.model;

import be.gerard.core.interface_v1.to.BaseTo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author bartgerard
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userDetail")
public class UserDetail extends BaseTo {

    public UserDetail() {
    }

    public UserDetail(String username, String firstname, String lastname, Date birthDate) {
        this(username, firstname, lastname, birthDate, null);
    }

    public UserDetail(String username, String firstname, String lastname, Date birthDate, Address homeAddress) {
        this(null, username, firstname, lastname, birthDate, homeAddress);

        if (homeAddress != null) {
            homeAddress.setHomeAddress(true);
            homeAddress.setPreferred(true);
            this.addresses.add(homeAddress);
        }
    }

    public UserDetail(Long id, String username, String firstname, String lastname, Date birthDate, Address homeAddress) {
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
    private Date birthDate;

    @XmlElement(required = true)
    private final List<Address> addresses = new ArrayList<>();

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    public void add(final Address address) {
        this.addresses.add(address);
    }

    public void remove(final Address address) {
        this.addresses.remove(address);
    }

    public Address getHomeAddress() {
        for (Address address : addresses) {
            if (address.isHomeAddress()) {
                return address;
            }
        }

        return null;
    }

    public Address getPreferredAddress() {
        for (Address address : addresses) {
            if (address.isPreferred()) {
                return address;
            }
        }

        return null;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.username);
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
        final UserDetail other = (UserDetail) obj;
        return Objects.equals(this.username, other.username);
    }

}
