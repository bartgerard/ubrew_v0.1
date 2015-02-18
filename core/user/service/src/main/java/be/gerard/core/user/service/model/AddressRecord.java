package be.gerard.core.user.service.model;

import be.gerard.common.db.model.BaseRecord;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author bartgerard
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_address", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "general_address")
public class AddressRecord extends BaseRecord {

    public AddressRecord() {
    }

    public AddressRecord(String addressee, String street, Integer houseNumber, String additional, String city, Integer zipOrPostalCode, String stateOrProvince, String country) {
        this.addressee = addressee;
        this.street = street;
        this.houseNumber = houseNumber;
        this.additional = additional;
        this.city = city;
        this.zipOrPostalCode = zipOrPostalCode;
        this.stateOrProvince = stateOrProvince;
        this.country = country;
    }

    @Column(name = "addressee", nullable = false)
    private String addressee;

    @Column(name = "street", nullable = false)
    private String street; // Street address, P.O. box

    @Column(name = "house_number", nullable = false)
    private Integer houseNumber;

    @Column(name = "additional", nullable = true)
    private String additional; // Apartment, suite, unit, building, floor, etc.

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "zipOrPostalCode", nullable = false)
    private Integer zipOrPostalCode;

    @Column(name = "stateOrProvince", nullable = false)
    private String stateOrProvince;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "homeAddress", nullable = false)
    private boolean homeAddress;

    @Column(name = "preferred", nullable = false)
    private boolean preferred;

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public Integer getZipOrPostalCode() {
        return zipOrPostalCode;
    }

    public void setZipOrPostalCode(Integer zipOrPostalCode) {
        this.zipOrPostalCode = zipOrPostalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(boolean homeAddress) {
        this.homeAddress = homeAddress;
    }

    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

}
