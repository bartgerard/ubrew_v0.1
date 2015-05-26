package be.gerard.general.service.dao;

import be.gerard.core.interface_v1.db.dao.DAO;
import be.gerard.general.service.model.UserDetailRecord;

/**
 *
 * @author bartgerard
 */
public interface UserDetailDAO extends DAO<UserDetailRecord, Long> {
    
    public UserDetailRecord getByUsername(String username);
    
}
