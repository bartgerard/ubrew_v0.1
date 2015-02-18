package be.gerard.general.service.decoder;

import be.gerard.common.decoder.Decoder;
import be.gerard.general.interface_v1.model.Address;
import be.gerard.general.service.model.AddressRecord;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 *
 * @author bartgerard
 */
@Component
public class AddressDecoder implements Decoder<AddressRecord, Address> {

    @Override
    public Address encode(final AddressRecord addressRecord) {
        Address address = new Address();

        address.setId(addressRecord.getId());
        address.setAddressee(addressRecord.getAddressee());
        address.setStreet(addressRecord.getStreet());
        address.setHouseNumber(addressRecord.getHouseNumber());
        address.setAdditional(addressRecord.getAdditional());
        address.setCity(addressRecord.getCity());
        address.setStateOrProvince(addressRecord.getStateOrProvince());
        address.setZipOrPostalCode(addressRecord.getZipOrPostalCode());
        address.setCountry(addressRecord.getCountry());
        address.setHomeAddress(addressRecord.isHomeAddress());
        address.setPreferred(addressRecord.isPreferred());

        return address;
    }

    @Override
    public AddressRecord decode(final Address address) {
        AddressRecord addressRecord = new AddressRecord();

        addressRecord.setId(address.getId());
        addressRecord.setAddressee(address.getAddressee());
        addressRecord.setStreet(address.getStreet());
        addressRecord.setHouseNumber(address.getHouseNumber());
        addressRecord.setAdditional(address.getAdditional());
        addressRecord.setCity(address.getCity());
        addressRecord.setStateOrProvince(address.getStateOrProvince());
        addressRecord.setZipOrPostalCode(address.getZipOrPostalCode());
        addressRecord.setCountry(address.getCountry());
        addressRecord.setHomeAddress(address.isHomeAddress());
        addressRecord.setPreferred(address.isPreferred());

        return addressRecord;
    }

    public void sync(final AddressRecord addressRecord, final Address address) {
        Assert.notNull(addressRecord, "invalid address record [null]");
        Assert.notNull(address, "invalid address [null]");

        addressRecord.setAddressee(address.getAddressee());
        addressRecord.setStreet(address.getStreet());
        addressRecord.setHouseNumber(address.getHouseNumber());
        addressRecord.setAdditional(address.getAdditional());
        addressRecord.setCity(address.getCity());
        addressRecord.setStateOrProvince(address.getStateOrProvince());
        addressRecord.setZipOrPostalCode(address.getZipOrPostalCode());
        addressRecord.setCountry(address.getCountry());
        addressRecord.setHomeAddress(address.isHomeAddress());
        addressRecord.setPreferred(address.isPreferred());
    }

}
