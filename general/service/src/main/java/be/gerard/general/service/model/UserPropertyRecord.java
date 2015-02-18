package be.gerard.general.service.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author bartgerard
 */
@Entity
@Table(name = "general_user_property")
public class UserPropertyRecord implements Serializable {

    public UserPropertyRecord() {
    }

    public UserPropertyRecord(final String application, final String key, final String value) {
        this(application, key, value, false);
    }

    public UserPropertyRecord(final String application, final String key, final String value, final boolean encrypted) {
        this.application = application;
        this.key = key;
        this.value = value;
        this.encrypted = encrypted;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "application", nullable = true)
    private String application;

    @Column(name = "propertyKey", nullable = false)
    private String key;

    @Column(name = "propertyValue", nullable = false)
    private String value;

    @Column(name = "encrypted", nullable = false)
    private boolean encrypted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.application);
        hash = 97 * hash + Objects.hashCode(this.key);
        hash = 97 * hash + Objects.hashCode(this.value);
        hash = 97 * hash + (this.encrypted ? 1 : 0);
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
        final UserPropertyRecord other = (UserPropertyRecord) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.application, other.application)) {
            return false;
        }
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return this.encrypted == other.encrypted;
    }

    @Override
    public String toString() {
        return "UserPropertyRecord{" + "id=" + id + ", application=" + application + ", key=" + key + ", value=" + value + ", encrypted=" + encrypted + '}';
    }

}
