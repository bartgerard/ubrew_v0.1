package be.gerard.sandbox.ubrew.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bartgerard
 */
@Entity
@Table(name = "address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findById", query = "SELECT a FROM Address a WHERE a.id = :id"),
    @NamedQuery(name = "Address.findByAddress1", query = "SELECT a FROM Address a WHERE a.address1 = :address1"),
    @NamedQuery(name = "Address.findByAddress2", query = "SELECT a FROM Address a WHERE a.address2 = :address2"),
    @NamedQuery(name = "Address.findByCity", query = "SELECT a FROM Address a WHERE a.city = :city"),
    @NamedQuery(name = "Address.findByCountry", query = "SELECT a FROM Address a WHERE a.country = :country"),
    @NamedQuery(name = "Address.findByHomeaddress", query = "SELECT a FROM Address a WHERE a.homeaddress = :homeaddress"),
    @NamedQuery(name = "Address.findByPreferred", query = "SELECT a FROM Address a WHERE a.preferred = :preferred"),
    @NamedQuery(name = "Address.findByStateorprovince", query = "SELECT a FROM Address a WHERE a.stateorprovince = :stateorprovince"),
    @NamedQuery(name = "Address.findByZiporpostalcode", query = "SELECT a FROM Address a WHERE a.ziporpostalcode = :ziporpostalcode")})
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address1")
    private String address1;
    @Size(max = 255)
    @Column(name = "address2")
    private String address2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Column(name = "homeaddress")
    private boolean homeaddress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preferred")
    private boolean preferred;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "stateorprovince")
    private String stateorprovince;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ziporpostalcode")
    private int ziporpostalcode;
    @JoinColumn(name = "addresses_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private GeneralUser addressesId;

    public Address() {
    }

    public Address(Long id) {
        this.id = id;
    }

    public Address(Long id, String address1, String city, String country, boolean homeaddress, boolean preferred, String stateorprovince, int ziporpostalcode) {
        this.id = id;
        this.address1 = address1;
        this.city = city;
        this.country = country;
        this.homeaddress = homeaddress;
        this.preferred = preferred;
        this.stateorprovince = stateorprovince;
        this.ziporpostalcode = ziporpostalcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(boolean homeaddress) {
        this.homeaddress = homeaddress;
    }

    public boolean getPreferred() {
        return preferred;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

    public String getStateorprovince() {
        return stateorprovince;
    }

    public void setStateorprovince(String stateorprovince) {
        this.stateorprovince = stateorprovince;
    }

    public int getZiporpostalcode() {
        return ziporpostalcode;
    }

    public void setZiporpostalcode(int ziporpostalcode) {
        this.ziporpostalcode = ziporpostalcode;
    }

    public GeneralUser getAddressesId() {
        return addressesId;
    }

    public void setAddressesId(GeneralUser addressesId) {
        this.addressesId = addressesId;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.gerard.sandbox.ubrew.Address[ id=" + id + " ]";
    }

}
