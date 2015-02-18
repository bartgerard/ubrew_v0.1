
package be.gerard.common.merger;

/**
 * Merger
 *
 * @author bartgerard
 * @version 0.0.1
 * 
 * @param <E> Entity
 * @param <T> TransportObject
 * @param <C> Context
 */
public interface Merger<E, T, C extends MergeContext> {

    default void merge(E entity, T transportObject) {
        merge(entity, transportObject, null);
    }

    void merge(E entity, T transportObject, C context);
    
}
