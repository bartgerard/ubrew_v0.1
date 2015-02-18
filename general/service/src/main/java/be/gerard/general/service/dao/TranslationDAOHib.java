package be.gerard.general.service.dao;

import be.gerard.common.db.dao.DAOHib;
import be.gerard.general.service.model.TranslationRecord;
import java.util.List;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserDAO Hibernate Implementation
 *
 * @author bartgerard
 * @version v0.0.1
 *
 */
@Profile("hib")
@Repository
public class TranslationDAOHib extends DAOHib<TranslationRecord, Long> implements TranslationDAO {

    @Transactional(readOnly = true)
    @Override
    public TranslationRecord find(final String application, final String language, final String key) {
        return (TranslationRecord) getSession().createCriteria(TranslationRecord.class)
                .add(Restrictions.ilike("application", application))
                .add(Restrictions.ilike("language", language))
                .add(Restrictions.ilike("key", key))
                .uniqueResult();
    }

    @Transactional(readOnly = true)
    @Override
    public List<TranslationRecord> findAll(final String application) {
        return getSession().createCriteria(TranslationRecord.class)
                .add(Restrictions.ilike("application", application))
                .list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> findAllSupportedLanguages() {
        return getSession().createCriteria(TranslationRecord.class)
                .setProjection(Projections.distinct(Projections.property("language")))
                .list();
    }

}
