package be.gerard.common.decoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Decoder
 * 
 * @author Bart Gerard
 * @param <E> Entity
 * @param <T> To
 */
public interface Decoder<E, T> {
    
    E decode(final T to);
    
    default List<E> decode(final List<T> tos) {
        List<E> result = new ArrayList<>();
        
        tos.stream().forEach((to) -> {
            result.add(decode(to));
        });
        
        return result;
    }
    
    T encode(final E entity);
    
    default List<T> encode(final List<E> entities) {
        List<T> result = new ArrayList<>();
        
        entities.stream().forEach((entity) -> {
            result.add(encode(entity));
        });
        
        return result;
    }
    
}
