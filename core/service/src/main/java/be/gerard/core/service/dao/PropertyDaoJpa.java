package be.gerard.core.service.dao;

import be.gerard.core.service.model.PropertyRecord;
import be.gerard.core.service.model.QApplicationRecord;
import be.gerard.core.service.model.QPropertyGroupRecord;
import be.gerard.core.service.model.QPropertyRecord;
import com.querydsl.jpa.JPAExpressions;
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

    public List<PropertyRecord> findAllForApp(final String app) {

        QPropertyRecord property = QPropertyRecord.propertyRecord;
        QPropertyRecord p = new QPropertyRecord("p");
        QPropertyGroupRecord pg = new QPropertyGroupRecord("pg");
        QApplicationRecord a = new QApplicationRecord("a");

        return query().from(property).where(property.id.in(
                JPAExpressions.select(p.id)
                        .from(p)
                        .groupBy(p.id)
                //.join(pg.properties, p)
                //.where(a.key.eq(app))
        )).fetch();
    }

}
