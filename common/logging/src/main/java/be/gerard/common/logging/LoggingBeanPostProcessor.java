package be.gerard.common.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * LoggingBeanPostProcessor
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Component
public class LoggingBeanPostProcessor implements BeanPostProcessor {

    static final Logger logger = LogManager.getLogger(LoggingBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // Log the names and types of all non inner-beans created
        if (!beanName.contains("inner bean")) {
            logger.info("NEW " + bean.getClass().getSimpleName() + " -> " + beanName);
        }
        return bean;
    }

}
