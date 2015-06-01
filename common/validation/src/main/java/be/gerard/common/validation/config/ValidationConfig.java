package be.gerard.common.validation.config;

import be.gerard.common.validation.ValidationService;
import be.gerard.common.validation.aop.ValidationAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * ValidationConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
@EnableAspectJAutoProxy
public class ValidationConfig {

    @Bean
    public ValidationService validationService() {
        return new ValidationService();
    }

    @Bean
    public ValidationAdvice validationAdvice() {
        return new ValidationAdvice();
    }

}
