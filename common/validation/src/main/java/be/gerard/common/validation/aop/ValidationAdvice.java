package be.gerard.common.validation.aop;

import be.gerard.common.annotation.validation.Validate;
import be.gerard.common.aop.utils.AnnotationUtils;
import be.gerard.common.validation.ErrorContext;
import be.gerard.common.validation.ValidationService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bartgerard
 */
@Aspect
final class ValidationAdvice implements Serializable {

    @Autowired
    private ValidationService validationService;

    @Around("be.gerard.common.aop.PointCutLibrary.springServiceOperation()")
    public Object validateService(final ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Annotation[][] parameterAnnotations = methodSignature.getMethod().getParameterAnnotations();

        List<ObjectError> validationErrors = new ArrayList<>();

        // START - Input Params
        for (int i = 0; i < pjp.getArgs().length; i++) {
            Validate validate = AnnotationUtils.getAnnotation(parameterAnnotations[i], Validate.class);

            if (validate != null) {
                Object object = pjp.getArgs()[i];
                String objectName = methodSignature.getParameterTypes()[i].getSimpleName();
                ErrorContext errors = new ErrorContext(object, objectName);
                validationService.validate(object, validate.type(), errors);
                validationErrors.addAll(errors.getAllErrors().stream().collect(Collectors.toList()));

            }
        }
        // END - Input Params

        validationService.throwOnError(validationErrors);

        return pjp.proceed();
    }

}
