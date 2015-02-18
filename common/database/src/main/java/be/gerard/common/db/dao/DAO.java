package be.gerard.common.db.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author bartgerard
 * @param <E>
 * @param <K>
 */
public interface DAO<E, K extends Serializable> {

    E find(final K id);

    E saveOrUpdate(final E e);

    void delete(final E e);
    
    int count();

    List<E> findAll();
    
    List<E> findRange(int[] range);
    
}
