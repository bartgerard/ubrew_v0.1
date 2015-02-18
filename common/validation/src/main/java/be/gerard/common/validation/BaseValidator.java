package be.gerard.common.validation;

import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * BaseValidator
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-11 12:45
 */
public abstract class BaseValidator<T> {

    private final Class<T> targetClass;

    private final String type;

    protected BaseValidator() {
        this(null);
    }

    protected BaseValidator(String type) {
        Assert.isInstanceOf(ParameterizedType.class, getClass().getGenericSuperclass(), String.format("genericSuperClass is invalid [%s]", getClass().getGenericSuperclass()));
        targetClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public boolean supports(Class<?> clazz) {
        return targetClass.equals(clazz);
    }

    public abstract void validate(T target, ErrorContext errorContext);

    // TODO
    public void validate(List<T> targets, ErrorContext errorContext) {
        for (T target : targets) {
            validate(target, errorContext);
        }
    }

}
