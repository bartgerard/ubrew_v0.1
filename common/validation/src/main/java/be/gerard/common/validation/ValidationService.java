package be.gerard.common.validation;

import be.gerard.common.exception_v1.ValidationException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;

import java.util.*;

/**
 * BaseValidationFactory
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-11 12:38
 */
@Component
public class ValidationService implements BeanPostProcessor {

    private final List<BaseValidator> validatorList = new ArrayList<>();

    private final Map<String, BaseValidator> validatorMap = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object validator, String beanName) throws BeansException {
        if (validator instanceof BaseValidator) {
            BaseValidator baseValidator = (BaseValidator) validator;
            if (StringUtils.hasText(baseValidator.getType())) {
                validatorMap.put(baseValidator.getType(), baseValidator);
            } else {
                validatorList.add(baseValidator);
            }
        }

        return validator;
    }

    public <T> void validate(List<T> targets, String type, ErrorContext errorContext) {
        for (T target : targets) {
            validate(target, type, errorContext);
        }
    }

    public <T> void validate(T target, String type, ErrorContext errorContext) {
        if ("".equals(type)) {
            validatorList.stream().filter(validator -> validator.supports(target.getClass())).forEach(validator -> {
                validator.validate(target, errorContext);
            });
        } else {
            validatorMap.get(type).validate(target, errorContext);
        }
    }

    public void throwOnError(Collection<ObjectError> validationErrors) {
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
    }

}
