package be.gerard.translation.service.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bartgerard
 * @param <E> Entity
 * @param <K> Key
 */
public abstract class DAOHib<E, K extends Serializable> implements DAO<E, K> {
    
    @Autowired(required = true)
    @Qualifier(value = "be.gerard.translation.sessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    protected static final Logger logger = LogManager.getLogger(DAO.class.getName());

    private Class<E> entityClass;

    protected Class<E> getEntityClass() {
        return entityClass;
    }

    public DAOHib() {
        if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
            entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        } else {
            throw new NullPointerException();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public E get(final K id) {
        logger.entry(id);
        return (E) logger.exit(getSession().get(entityClass, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<E> list() {
        logger.entry();
        return logger.exit(getSession().createCriteria(entityClass).list());
    }

    @Transactional(readOnly = false)
    @Override
    public E saveOrUpdate(final E e) {
        logger.entry(e);
        getSession().saveOrUpdate(e);
        return logger.exit(e);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(final E e) {
        logger.entry(e);
        getSession().delete(e);
        logger.exit();
    }

}
