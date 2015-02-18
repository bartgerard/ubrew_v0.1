package be.gerard.general.service.model;

import be.gerard.common.db.model.BaseRecord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;
import org.springframework.util.Assert;

/**
 *
 * @author bartgerard
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_user_detail", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "general_user_detail", uniqueConstraints = @UniqueConstraint(name = "uk_userdetail_username", columnNames = "username"))
public class UserDetailRecord extends BaseRecord {

    public UserDetailRecord() {
    }

    public UserDetailRecord(String username, String firstname, String lastname, Date birthDate) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
    }

    public UserDetailRecord(String username, String firstname, String lastname, Date birthDate, AddressRecord address) {
        this(username, firstname, lastname, birthDate);

        if (address != null) {
            this.addresses.add(address);
            address.setHomeAddress(true);
            address.setPreferred(true);
        }
    }

    @NaturalId
    @Column(name = "username", nullable = false, updatable = false)
    private String username;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate", nullable = false)
    private Date birthDate;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(nullable = true)
    private final List<AddressRecord> addresses = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "user_detail_id", nullable = true, foreignKey = @ForeignKey(name = "fk_user_property"))
    private final List<UserPropertyRecord> properties = new ArrayList<>();

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

    public List<AddressRecord> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    public void addAddress(final AddressRecord addressRecord) {
        Assert.notNull(addressRecord, "invalid address record [null]");
        Assert.isTrue(this.addresses.add(addressRecord), String.format("add address record failed [%s]", addressRecord.toString()));
    }

    public void removeAddress(final AddressRecord addressRecord) {
        Assert.notNull(addressRecord, "invalid address record [null]");
        Assert.isTrue(this.addresses.remove(addressRecord), String.format("remove address record failed [%s]", addressRecord.toString()));
    }

    public AddressRecord getHomeAddress() {
        for (AddressRecord addressRecord : addresses) {
            if (addressRecord.isHomeAddress()) {
                return addressRecord;
            }
        }

        return null;
    }

    public AddressRecord getAddress(final Long id) {
        for (AddressRecord addressRecord : addresses) {
            if (addressRecord.getId().equals(id)) {
                return addressRecord;
            }
        }

        return null;
    }

    public List<UserPropertyRecord> getProperties() {
        return Collections.unmodifiableList(properties);
    }

    public void put(final String application, final String key, final String value) {
        Assert.notNull(application, "invalid application [null]");
        Assert.notNull(key, "invalid key [null]");
        Assert.notNull(value, "invalid value [null]");

        UserPropertyRecord propertyRecord = getProperty(application, key);

        if (propertyRecord != null) {
            propertyRecord.setValue(value);
        } else {
            addProperty(new UserPropertyRecord(application, key, value));
        }
    }

    public void addProperty(final UserPropertyRecord propertyRecord) {
        Assert.notNull(propertyRecord, "invalid property record [null]");
        Assert.isTrue(this.properties.add(propertyRecord), String.format("add property record failed [%s]", propertyRecord.toString()));
    }

    public void removeProperty(final UserPropertyRecord propertyRecord) {
        Assert.notNull(propertyRecord, "invalid property record [null]");
        Assert.isTrue(this.properties.remove(propertyRecord), String.format("remove property record failed [%s]", propertyRecord.toString()));
    }

    public UserPropertyRecord getProperty(final String application, final String key) {
        Assert.hasText(application, String.format("invalid application [%s]", application));
        Assert.hasText(key, String.format("invalid application [%s]", key));

        for (UserPropertyRecord property : properties) {
            if (property.getApplication().equals(application) && property.getApplication().equals(key)) {
                return property;
            }
        }

        return null;
    }

}
