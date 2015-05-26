package be.gerard.common.aop;

import be.gerard.common.exception_v1.BusinessException;
import be.gerard.common.exception_v1.TechnicalException;
import be.gerard.common.exception_v1.error.CommonTechnicalError;
import be.gerard.common.exception_v1.internal.ContextualException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.Assert;

/**
 *
 * @author bartgerard
 */
@Aspect
public class ServiceExceptionAdvice implements Serializable {
    
    private TechnicalException.Error technicalError = CommonTechnicalError.UNEXPECTED;

    public void setDefaultTechnicalError(final TechnicalException.Error technicalError) {
        Assert.notNull(technicalError, "[NULL] default error");
        this.technicalError = technicalError;
    }

    public void handleException(final JoinPoint call, final Throwable exception) throws Throwable {
        if (exception instanceof BusinessException) {
            throw exception;
        }

        // Get the logger that handles the target instance of the method execution.
        Signature signature = call.getSignature();
        Logger logger = LogManager.getLogger(signature.getDeclaringType());

        MethodSignature methodSignature = (MethodSignature) signature;
        // Compute the method information
        String methodName = signature.toShortString();
        String targetName = signature.getDeclaringTypeName();
        Type returnType = methodSignature.getReturnType();

        // Compute the method parameters and prepare the exception context
        Map<String, String> exceptionContext = new LinkedHashMap<>();
        exceptionContext.put("request", call.getSignature().toLongString());
        exceptionContext.put("service name", targetName);
        exceptionContext.put("method name", methodName);
        
        if (returnType != null) {
            exceptionContext.put("return parameter", String.valueOf(returnType.getClass().getCanonicalName()));
        }

        // AspectJ compiler has to be used to retrieve the parameter names
        Object[] parameterValues = call.getArgs();
        String[] parameterNames = new String[parameterValues.length];
        
        if ((methodSignature.getParameterNames() != null) && methodSignature.getParameterNames().length == parameterNames.length) {
            parameterNames = methodSignature.getParameterNames();
        }
        
        for (int i = 0; i < parameterValues.length; i++) {
            String parameterName = (parameterNames[i] == null ? String.valueOf(i) : parameterNames[i]);
            exceptionContext.put(String.format("method parameter [%s]", parameterName), String.valueOf(parameterValues[i]));
        }

        // Prepare the contextual exception to be logged.
        String exceptionText = "An unexpected error was encountered while processing the service request.";
        ContextualException contextualException = new ContextualException(exceptionText, exception, exceptionContext);
        // Log the exception
        logger.error(contextualException.toString());
        // Throw the technical exception with the facadeException id.
        throw new TechnicalException(exceptionText, technicalError, String.format("#%s", contextualException.getId()));
    }
    
}
