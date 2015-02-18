package be.gerard.admin.repositories.template;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Data Access Object Templates.
 *
 * @author Bart Gerard
 * @version v0.0.1
 * @param <E>
 * @param <K>
 *
 */
@Transactional(readOnly = true)
// Default is read only
public abstract class DAOTemplate<E, K extends Serializable> {
    
    private static Logger logger = LogManager.getLogger();

    private Class<E> persistentClass;

    protected Class<E> getPersistentClass() {
        return persistentClass;
    }

    @Autowired
    private SessionFactory sessionFactory;

//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public DAOTemplate() {
        logger.entry();
        try {
            if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
                persistentClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            }
        } catch (Exception e) {
            logger.throwing(e);
        }
        logger.exit();
    }

    public Query getNamedQuery(String queryName) {
        logger.entry();
        return logger.exit(getSession().getNamedQuery(queryName));
    }

    public Query getNativeQuery(String queryString) {
        logger.entry();
        return logger.exit(getSession().createQuery(queryString));
    }

    @SuppressWarnings("unchecked")
    public E get(final K id) {
        logger.entry(id);
        return (E) logger.exit(getSession().get(persistentClass, id));
    }

    @SuppressWarnings("unchecked")
    public List<E> list() {
        logger.entry();
        return logger.exit(getSession().createCriteria(persistentClass).list());
    }

    @Transactional(readOnly = false)
    public E saveOrUpdate(E entity) {
        logger.entry(entity);
        getSession().saveOrUpdate(entity);
        getSession().flush();
        return logger.exit(entity);
    }

    @Transactional(readOnly = false)
    public void delete(E entity) {
        logger.entry();
        getSession().delete(entity);
        logger.exit();
    }

}
