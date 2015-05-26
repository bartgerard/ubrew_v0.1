package be.gerard.general.service.dao;

import be.gerard.core.interface_v1.db.dao.DAOHib;
import be.gerard.general.service.model.UserRecord;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserDAOHib
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Profile("hib")
@Repository
public class UserDAOHib extends DAOHib<UserRecord, String> implements UserDAO {

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @Transactional(readOnly = false)
    @Override
    public UserRecord saveOrUpdate(UserRecord userRecord) {
        userRecord.setPassword(passwordEncryptor.encryptPassword(userRecord.getPassword()));
        return super.saveOrUpdate(userRecord);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean validateLogin(String username, String password) {
        UserRecord userRecord = find(username);
        return userRecord != null && passwordEncryptor.checkPassword(password, userRecord.getPassword());
    }

}
