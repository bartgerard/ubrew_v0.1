package be.gerard.core.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * SecurityWebApplicationInitializer
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(CoreWebConfig.class, SecurityConfig.class);
    }

}