package be.gerard.general.service.dao;

import be.gerard.core.interface_v1.db.dao.DAO;
import be.gerard.general.service.model.UserRecord;

/**
 * UserDAO
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface UserDAO extends DAO<UserRecord, String> {

    @Override
    UserRecord saveOrUpdate(UserRecord e);
    
    boolean validateLogin(String username, String password);

}
