package be.gerard.common.security.config;

import be.gerard.common.security.CommonAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AuthenticationConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
public class AuthenticationConfig {

    @Bean
    public CommonAuthenticationProvider commonAuthenticationProvider() {
        return new CommonAuthenticationProvider();
    }

}
