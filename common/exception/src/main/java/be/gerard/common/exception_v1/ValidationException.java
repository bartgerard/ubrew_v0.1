package be.gerard.common.exception_v1;

import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ValidationException
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-12 20:31
 */
public class ValidationException extends RuntimeException {

    private final List<ObjectError> errors = new ArrayList<>();

    public ValidationException() {
    }

    public ValidationException(Collection<ObjectError> errors) {
        this.errors.addAll(errors);
    }

    public void add(ObjectError error) {
        this.errors.add(error);
    }

    public void addAll(Collection<ObjectError> errors) {
        this.errors.addAll(errors);
    }

}
