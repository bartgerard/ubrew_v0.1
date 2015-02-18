package be.gerard.general.service.dao;

import be.gerard.common.db.dao.DAOHib;
import be.gerard.general.service.model.ApplicationDetailRecord;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * ApplicationDetailDAOJpa
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Profile("hib")
@Repository
public class ApplicationDetailDAOHib extends DAOHib<ApplicationDetailRecord, Long> implements ApplicationDetailDAO {
    
    @Transactional(readOnly = true)
    @Override
    public ApplicationDetailRecord findByKey(String key) {
        return (ApplicationDetailRecord) getSession().createCriteria(ApplicationDetailRecord.class).add(Restrictions.ilike("key", key)).uniqueResult();
    }

}
