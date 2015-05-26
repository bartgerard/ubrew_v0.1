package be.gerard.general.service.dao;

import be.gerard.core.interface_v1.db.dao.DAOHib;
import be.gerard.general.service.model.ApplicationRecord;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * ApplicationDAOJpa
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Profile("hib")
@Repository
public class ApplicationDAOHib extends DAOHib<ApplicationRecord, String> implements ApplicationDAO {
    
    @Autowired
    private PasswordEncryptor passwordEncryptor;
    
    private ApplicationRecord findByKey(String key) {
        return (ApplicationRecord) getSession().createCriteria(ApplicationRecord.class).add(Restrictions.ilike("key", key)).uniqueResult();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean validateLogin(String key, String password) {
        ApplicationRecord applicationRecord = findByKey(key);
        return applicationRecord != null && passwordEncryptor.checkPassword(password, applicationRecord.getPassword());
    }

}
