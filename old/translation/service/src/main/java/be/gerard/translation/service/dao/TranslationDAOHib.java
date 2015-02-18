package be.gerard.translation.service.dao;

import be.gerard.translation.service.model.TranslationRecord;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserDAO Hibernate Implementation
 *
 * @author bartgerard
 * @version v0.0.1
 *
 */
@Repository
public class TranslationDAOHib extends DAOHib<TranslationRecord, Long> implements TranslationDAO {

    @Transactional(readOnly = true)
    @Override
    public TranslationRecord get(final String application, final String language, final String key) {
        logger.entry(application, language, key);
        return (TranslationRecord) logger.exit(getSession().createCriteria(TranslationRecord.class)
                .add(Restrictions.ilike("application", application))
                .add(Restrictions.ilike("language", language))
                .add(Restrictions.ilike("key", key))
                .uniqueResult());
    }

}
