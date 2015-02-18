/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gerard.ubrew.database.dao.hibernate;

import be.gerard.ubrew.database.dao.DAO;
import be.gerard.ubrew.database.util.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author bartgerard
 * @param <E> Entity
 * @param <K> Key
 */
public class DAOHib<E, K extends Serializable> implements DAO<E, K> {
    
    private static final Logger logger = LogManager.getLogger(DAO.class.getName());

    private Class<E> persistentClass;

    protected Class<E> getPersistentClass() {
        return persistentClass;
    }
    
    protected Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public DAOHib() {
        if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
            persistentClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public E get(final K id) {
        logger.entry(id);
        Session session = openSession();
        E entity = (E) session.get(persistentClass, id);
        session.close();
        return logger.exit(entity);
    }

    @Override
    public List<E> list() {
        logger.entry();
        Session session = openSession();
        List<E> list = session.createCriteria(persistentClass).list();
        session.close();
        return logger.exit(list);
    }

    @Override
    public E saveOrUpdate(final E e) {
        logger.entry(e);
        Session session = openSession();
        session.beginTransaction();
        session.saveOrUpdate(e);
        session.getTransaction().commit();
        session.close();
        return logger.exit(e);
    }

    @Override
    public void delete(final E e) {
        logger.entry(e);
        Session session = openSession();
        session.beginTransaction();
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        logger.exit();
    }

}
