package be.gerard.common.aop;

import java.io.Serializable;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Library containing Pointcut definitions.
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class PointCutLibrary implements Serializable {

    /**
     * Matches the execution of any method in package with prefix be.gerard.general.
     */
    @Pointcut("within(be.gerard.general..*)")
    public void withinGeneral() {
    }

    /**
     * Matches the execution of any public method.
     */
    @Pointcut("execution(public * *(..))")
    public void anyPublicOperation() {
    }

    /**
     * Matches the execution of any public operation within general.
     */
    @Pointcut("anyPublicOperation() && withinGeneral()")
    public void generalOperation() {
    }

//    /**
//     * Matches the execution of any public general operation, where the code
//     * executing is declared as a Stateless Session Bean.
//     */
//    @Pointcut("generalOperation() && within(@javax.ejb.Stateless *)")
//    public void ejbServiceOperation() {
//    }

    /**
     * Matches the execution of any public general operation, where the code
     * executing is declared in a type with an @Service annotation.
     */
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void springServiceOperation() {
    }

//    @Pointcut("ejbServiceOperation() || springServiceOperation()")
//    public void serviceOperation() {
//    }

    /**
     * Matches the execution of any public general operation, where the code
     * executing is declared in a type with an @Repository annotation.
     */
    @Pointcut("generalOperation() && within(@org.springframework.stereotype.Repository *)")
    public void springRepositoryOperation() {
    }

    /**
     * Matches the execution of any public general operation, where the code
     * executing is declared in a type with an @Controller annotation.
     */
    @Pointcut("generalOperation() && within(@org.springframework.stereotype.Controller *)")
    public void springControllerOperation() {
    }

}
