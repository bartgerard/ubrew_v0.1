package be.gerard.general.service;

import be.gerard.general.interface_v1.UserService;
import be.gerard.general.interface_v1.exception.UserServiceException;
import be.gerard.general.interface_v1.exception.error.UserServiceError;
import be.gerard.general.interface_v1.model.UserDetail;
import be.gerard.general.service.dao.UserDAO;
import be.gerard.general.service.dao.UserDetailDAO;
import be.gerard.general.service.decoder.UserDetailDecoder;
import be.gerard.general.service.model.UserDetailRecord;
import be.gerard.general.service.model.UserRecord;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bartgerard
 */
@Service // BGE: @Service is not required for remoting. (But used here for ContextScan)
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserDetailDAO userDetailDAO;

    @Autowired
    private UserDetailDecoder userDetailDecoder;

    @Transactional(readOnly = false)
    @Override
    public UserDetail saveOrUpdate(final UserDetail user, final String password, final String application) throws UserServiceException {
        if (user.getId() == null && userDAO.find(user.getUsername()) != null) {
            throw new UserServiceException("UsernameAlreadyExists", UserServiceError.USERNAME_ALREADY_EXISTS, user.getUsername());
        } else {
            userDAO.saveOrUpdate(new UserRecord(user.getUsername(), password));
        }

        UserDetailRecord userRecord = userDetailDecoder.decode(user, application);

        return userDetailDecoder.encode(userDetailDAO.saveOrUpdate(userRecord), application);
    }

    @Override
    public UserDetail find(final Long id, final String application) {
        return userDetailDecoder.encode(userDetailDAO.find(id), application);
    }

    @Override
    public int count() {
        return userDetailDAO.count();
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(final UserDetail user) {
        userDetailDAO.delete(userDetailDAO.find(user.getId()));
    }

    @Override
    public List<UserDetail> findAll(final String application) {
        return userDetailDecoder.encode(userDetailDAO.findAll(), application);
    }

}
