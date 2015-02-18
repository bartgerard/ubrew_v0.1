package be.gerard.translation.service.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author bartgerard
 * @param <E>
 * @param <K>
 */
public interface DAO<E, K extends Serializable> {

    E get(final K id);

    List<E> list();

    E saveOrUpdate(final E e);

    void delete(final E e);
    
}
