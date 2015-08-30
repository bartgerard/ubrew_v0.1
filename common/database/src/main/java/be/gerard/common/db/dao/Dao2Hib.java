package be.gerard.common.db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @param <E> Entity
 * @param <K> Key
 * @author bartgerard
 */
@Profile("hib")
public abstract class Dao2Hib<E, K extends Serializable> implements Dao2<E, K> {

    private final Class<E> entityClass;
    @Autowired(required = true)
    //@Qualifier(value = "sessionFactory")
    private SessionFactory sessionFactory;

    public Dao2Hib() {
        Assert.isInstanceOf(ParameterizedType.class, getClass().getGenericSuperclass(), String.format("genericSuperClass is invalid [%s]", getClass().getGenericSuperclass()));
        entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Class<E> getEntityClass() {
        return entityClass;
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
