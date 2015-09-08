package be.gerard.common.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AopConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("be.gerard.common.aop.advice")
public class AopConfig {

}
