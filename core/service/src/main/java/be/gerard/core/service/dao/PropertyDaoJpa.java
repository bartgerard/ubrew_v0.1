package be.gerard.core.service.dao;

import be.gerard.core.service.model.PropertyRecord;
import be.gerard.core.service.model.QPropertyRecord;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * PropertyDaoJpa
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Repository
@Transactional
public class PropertyDaoJpa implements PropertyDao {

    @PersistenceContext
    private EntityManager entityManager;

    protected JPAQuery<PropertyRecord> query() {
        return new JPAQuery<>(entityManager);
    }

    @Override
    public PropertyRecord findOne(Long id) {
        // TEST
        QPropertyRecord property = QPropertyRecord.propertyRecord;
        return query().from(property).where(property.id.eq(id)).fetchOne();
    }

    public List<PropertyRecord> findByApp(final String app) {
        QPropertyRecord property = QPropertyRecord.propertyRecord;
        //return query().from(property).where(property.id.eq(id)).fetchOne();
        return null;
    }

}
