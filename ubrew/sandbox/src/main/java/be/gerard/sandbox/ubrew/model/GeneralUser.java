package be.gerard.sandbox.ubrew.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bartgerard
 */
@Entity
@Table(name = "general_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneralUser.findAll", query = "SELECT g FROM GeneralUser g"),
    @NamedQuery(name = "GeneralUser.findById", query = "SELECT g FROM GeneralUser g WHERE g.id = :id"),
    @NamedQuery(name = "GeneralUser.findByBirthdate", query = "SELECT g FROM GeneralUser g WHERE g.birthdate = :birthdate"),
    @NamedQuery(name = "GeneralUser.findByFirstname", query = "SELECT g FROM GeneralUser g WHERE g.firstname = :firstname"),
    @NamedQuery(name = "GeneralUser.findByLastname", query = "SELECT g FROM GeneralUser g WHERE g.lastname = :lastname"),
    @NamedQuery(name = "GeneralUser.findByPassword", query = "SELECT g FROM GeneralUser g WHERE g.password = :password"),
    @NamedQuery(name = "GeneralUser.findByUsername", query = "SELECT g FROM GeneralUser g WHERE g.username = :username")})
public class GeneralUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressesId", fetch = FetchType.EAGER)
    private Set<Address> addressSet;

    public GeneralUser() {
    }

    public GeneralUser(Long id) {
        this.id = id;
    }

    public GeneralUser(Long id, Date birthdate, String firstname, String lastname, String password, String username) {
        this.id = id;
        this.birthdate = birthdate;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlTransient
    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneralUser)) {
            return false;
        }
        GeneralUser other = (GeneralUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.gerard.sandbox.ubrew.GeneralUser[ id=" + id + " ]";
    }

}
