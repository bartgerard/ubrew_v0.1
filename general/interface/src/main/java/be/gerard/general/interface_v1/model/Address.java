package be.gerard.general.interface_v1.model;

import be.gerard.common.to.BaseTo;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author bartgerard
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "address")
public class Address extends BaseTo {

    public Address() {
    }

    public Address(Long id, String addressee, String street, Integer houseNumber, String additional, String city, Integer zipOrPostalCode, String stateOrProvince, String country) {
        super(id);
        this.addressee = addressee;
        this.street = street;
        this.houseNumber = houseNumber;
        this.additional = additional;
        this.city = city;
        this.zipOrPostalCode = zipOrPostalCode;
        this.stateOrProvince = stateOrProvince;
        this.country = country;
    }

    public Address(String addressee, String street, Integer houseNumber, String additional, String city, Integer zipOrPostalCode, String stateOrProvince, String country) {
        this(null, addressee, street, houseNumber, additional, city, zipOrPostalCode, stateOrProvince, country);
    }

    @XmlElement(required = true)
    private String addressee;

    @XmlElement(required = true)
    private String street; // Street address, P.O. box

    @XmlElement(required = true)
    private Integer houseNumber;

    @XmlElement
    private String additional; // Apartment, suite, unit, building, floor, etc.

    @XmlElement(required = true)
    private String city;

    @XmlElement(required = true)
    private Integer zipOrPostalCode;

    @XmlElement(required = true)
    private String stateOrProvince;

    @XmlElement(required = true)
    private String country;

    @XmlElement(required = true)
    private boolean homeAddress = false;

    @XmlElement(required = true)
    private boolean preferred = false;

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
