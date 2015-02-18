package be.gerard.common.validation;

import be.gerard.common.exception_v1.BusinessException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Validation
 * 
 * @author bartgerard
 * @param <T>
 */
public interface Validation<T> extends Serializable {
    
    boolean isApplicable(final Object object);
    
    void validate(final T object, final Map<BusinessException.Error, String[]> errors);

    default void validateList(final List<T> objects, final Map<BusinessException.Error, String[]> errors) {
        objects.stream().forEach((t) -> {
            validate(t, errors);
        });
    }
    
}
