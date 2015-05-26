package be.gerard.core.service;

import be.gerard.core.interface_v1.UserService;
import be.gerard.core.interface_v1.model.User;
import be.gerard.core.service.dao.UserDao;
import be.gerard.core.service.merger.UserMerger;
import be.gerard.core.service.model.UserRecord;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserServiceImpl
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private UserMerger userMerger;

    @Override
    public User save(User user, String password) {
        // Retrieve userRecord
        UserRecord userRecord = userDao.findByUsername(user.getUsername());

        if (userRecord == null) {
            userRecord = new UserRecord();
        }

        // Merge userRecord
        userMerger.merge(userRecord, user);

        // Set and encryptPassword
        userRecord.setEncryptedPassword(passwordEncryptor.encryptPassword(password));

        // Save userRecord
        userDao.saveAndFlush(userRecord);

        // Convert userRecord
        return conversionService.convert(userRecord, User.class);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        UserRecord userRecord = userDao.findByUsername(username);

        if (!passwordEncryptor.checkPassword(password, userRecord.getEncryptedPassword())) {
            throw new IllegalArgumentException();
        }

        return conversionService.convert(userRecord, User.class);
    }

    @Override
    public User findByUsername(String username) {
        return conversionService.convert(userDao.findByUsername(username), User.class);
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();

        for (UserRecord userRecord : userDao.findAll()) {
            result.add(conversionService.convert(userRecord, User.class));
        }

        return result;
    }

}
