package be.gerard.general.service.decoder;

import be.gerard.common.decoder.Decoder;
import be.gerard.general.interface_v1.model.UserDetail;
import be.gerard.general.service.dao.UserDetailDAO;
import be.gerard.general.service.model.AddressRecord;
import be.gerard.general.service.model.UserDetailRecord;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 *
 * @author bartgerard
 */
@Component
public class UserDetailDecoder implements Decoder<UserDetailRecord, UserDetail> {

    @Autowired
    private UserDetailDAO userDAO;

    @Autowired
    private AddressDecoder addressDecoder;

    public List<UserDetail> encode(final List<UserDetailRecord> userRecords, final String application) {
        List<UserDetail> result = new ArrayList<>();

        userRecords.stream().forEach((userRecord) -> {
            result.add(encode(userRecord, application));
        });

        return result;
    }

    public UserDetail encode(final UserDetailRecord userRecord, final String application) {
        if (userRecord == null) {
            return null;
        }

        UserDetail user = new UserDetail();

        user.setId(userRecord.getId());
        user.setUsername(userRecord.getUsername());
        user.setFirstname(userRecord.getFirstname());
        user.setLastname(userRecord.getLastname());
        user.setBirthDate(userRecord.getBirthDate());

        userRecord.getAddresses().stream().forEach((addressRecord) -> {
            user.add(addressDecoder.encode(addressRecord));
        });

        userRecord.getProperties().stream().filter((propertyRecord) -> (propertyRecord.getApplication().equals(application))).forEach((propertyRecord) -> {
            user.put(propertyRecord.getKey(), propertyRecord.getValue());
        });

        return user;
    }

    public UserDetailRecord decode(final UserDetail userDetail, final String application) {
        if (userDetail == null) {
            return null;
        }

        final UserDetailRecord userDetailRecord = userDetail.getId() != null ? userDAO.find(userDetail.getId()) : new UserDetailRecord();
        Assert.notNull(userDetailRecord, String.format("user not found with id [%d]", userDetail.getId()));

        userDetailRecord.setUsername(userDetail.getUsername());
        userDetailRecord.setFirstname(userDetail.getFirstname());
        userDetailRecord.setLastname(userDetail.getLastname());
        userDetailRecord.setBirthDate(userDetail.getBirthDate());

        userDetail.getAddresses().stream().forEach((address) -> {
            AddressRecord addressRecord = userDetailRecord.getAddress(address.getId());
            
            if (addressRecord != null) {
                addressDecoder.sync(addressRecord, address);
            } else {
                userDetailRecord.addAddress(addressDecoder.decode(address));
            }
        });

        userDetail.getProperties().entrySet().stream().forEach((entry) -> {
            userDetailRecord.put(application, entry.getKey(), entry.getValue());
        });

        return userDetailRecord;
    }

    @Override
    public UserDetailRecord decode(UserDetail to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDetail encode(UserDetailRecord entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
