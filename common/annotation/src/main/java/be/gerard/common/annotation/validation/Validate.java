package be.gerard.common.annotation.validation;

import java.lang.annotation.*;

/**
 * Validate
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-12 20:53
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validate {

    String type() default "";

}
