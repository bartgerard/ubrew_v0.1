package be.gerard.general.service.dao;

import be.gerard.core.interface_v1.db.dao.DAOJpa;
import be.gerard.general.service.model.UserDetailRecord;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * UserDAO Hibernate Implementation
 *
 * @author bartgerard
 * @version v0.0.1
 * 
 */
@Profile("jpa")
@Repository
public class UserDetailDAOJpa extends DAOJpa<UserDetailRecord, Long> implements UserDetailDAO {

    @Transactional(readOnly = true)
    @Override
    public UserDetailRecord getByUsername(String username) {
        return (UserDetailRecord) getSession().createCriteria(UserDetailRecord.class).add(Restrictions.ilike("username", username)).uniqueResult();
    }

}
