package be.gerard.general.interface_v1;

import java.util.List;

/**
 * BaseCrudService
 *
 * @author bartgerard
 * @version 0.0.1
 * @param <T> Transport Object (TO)
 * @param <K> Primary Key
 */
public interface BaseCrudService<T, K> extends BaseService {
    
    T find(K id);
    
    T saveOrUpdate(T to);
    
    void remove(T to);
    
    List<T> findAll();

}
