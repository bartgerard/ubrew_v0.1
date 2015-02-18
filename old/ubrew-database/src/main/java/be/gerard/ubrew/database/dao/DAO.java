/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.gerard.ubrew.database.dao;

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
