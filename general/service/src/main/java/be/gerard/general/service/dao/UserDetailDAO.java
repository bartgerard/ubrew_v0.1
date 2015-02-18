package be.gerard.general.service.dao;

import be.gerard.common.db.dao.DAO;
import be.gerard.general.service.model.UserDetailRecord;

/**
 *
 * @author bartgerard
 */
public interface UserDetailDAO extends DAO<UserDetailRecord, Long> {
    
    public UserDetailRecord getByUsername(String username);
    
}
