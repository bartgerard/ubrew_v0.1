package be.gerard.common.validation.aop;

import be.gerard.common.annotation.validation.Validation;
import be.gerard.common.aop.PointCutLibrary;
import java.io.Serializable;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Library containing Validation Pointcut definitions.
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class ValidationPointCutLibrary extends PointCutLibrary implements Serializable {
    
    @Pointcut("@annotation(validation)")
    public void validate(Validation validation) {
        
    }
    
    @Pointcut("springServiceOperation() && validate(validation)")
    public void validateService(Validation validation) {
        
    }

}
