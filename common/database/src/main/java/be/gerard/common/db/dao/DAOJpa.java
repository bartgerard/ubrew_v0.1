package be.gerard.common.db.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 *
 * @author bartgerard
 * @param <E> Entity
 * @param <K> Key
 */
@Profile("jpa")
@Transactional(readOnly = true)
public abstract class DAOJpa<E, K extends Serializable> implements DAO<E, K> {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Session getSession() {
        // JPA 1.0
        //return (Session) getEntityManager().getDelegate();

        // JPA 2.0
        return getEntityManager().unwrap(Session.class);
    }

    private final Class<E> entityClass;

    protected Class<E> getEntityClass() {
        return entityClass;
    }

    public DAOJpa() {
        Assert.isInstanceOf(ParameterizedType.class, getClass().getGenericSuperclass(), String.format("genericSuperClass is invalid [%s]", getClass().getGenericSuperclass()));
        entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Transactional(readOnly = true)
    @Override
    public E find(final K id) {
        return (E) getSession().get(entityClass, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<E> findAll() {
        return getSession().createCriteria(entityClass).list();
    }

    @Transactional(readOnly = false)
    @Override
    public E saveOrUpdate(final E e) {
        getSession().saveOrUpdate(e);
        return e;
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(final E e) {
        getSession().delete(e);
    }

    @Transactional(readOnly = true)
    @Override
    public int count() {
        return getSession().createCriteria(entityClass).list().size();
    }

    @Transactional(readOnly = true)
    @Override
    public List<E> findRange(int[] range) {
        Assert.isTrue(range.length == 2, String.format("range has invalid length [%d]", range.length));
        Assert.isTrue(range[0] <= range[1], String.format("range has invalid range [%d - %d]", range[0], range[1]));

        return getSession().createCriteria(entityClass)
                .setMaxResults(range[1] - range[0] + 1)
                .setFirstResult(range[0])
                .list();
    }

}
