package be.gerard.common.validation.aop;

import be.gerard.common.annotation.validation.Validation;
import be.gerard.common.aop.util.AnnotationUtils;
import be.gerard.common.exception_v1.BusinessException;
import be.gerard.common.validation.Validations;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bartgerard
 */
@Aspect
final class ValidationAdviceOld implements Serializable {

    @Autowired
    private Validations validations;

    @Around("be.gerard.common.aop.PointCutLibrary.springServiceOperation()")
    public Object validateService(final ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Annotation[][] parameterAnnotations = methodSignature.getMethod().getParameterAnnotations();

        Map<BusinessException.Error, String[]> errors = new HashMap<>();

        // START - Input Params
        for (int i = 0; i < pjp.getArgs().length; i++) {
            Validation validation = AnnotationUtils.getAnnotation(parameterAnnotations[i], Validation.class);

            if (validation != null) {
                for (String validator : validation.validators()) {
                    validations.validate(validator, pjp.getArgs()[i], errors);
                }
            }
        }
        // END - Input Params

        validations.handle(errors);

        return pjp.proceed();
    }

}
