package be.gerard.general.service.dao;

import be.gerard.common.db.dao.DAO;
import be.gerard.general.service.model.ApplicationRecord;

/**
 * ApplicationDAO
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface ApplicationDAO extends DAO<ApplicationRecord, String> {

    boolean validateLogin(String key, String password);

}
