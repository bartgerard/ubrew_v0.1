package be.gerard.common.aop.utils;

import java.lang.annotation.Annotation;
import org.springframework.util.Assert;

/**
 * AnnotationUtils
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class AnnotationUtils {

    private AnnotationUtils() {
    }

    public static <T extends Annotation> T getAnnotation(Annotation[] annotations, Class<T> annotationClass) {
        Assert.notNull(annotationClass, "annotationClass is invalid [null]");

        for (Annotation annotation : annotations) {
            if (annotationClass.isInstance(annotation)) {
                return (T) annotation;
            }
        }

        return null;
    }

}
