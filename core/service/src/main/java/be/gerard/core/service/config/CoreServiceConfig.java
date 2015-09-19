package be.gerard.core.service.config;

import be.gerard.common.converter.config.ConverterConfig;
import be.gerard.common.properties.EnvironmentPropertyPlaceholderConfigurer;
import be.gerard.common.validation.config.ValidationConfig;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * ApplicationConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
@Import({ConverterConfig.class, ValidationConfig.class})
@ComponentScan({
        "be.gerard.core.service",
        "be.gerard.common.validation.config",
        "be.gerard.common.converter.config"
})
public class CoreServiceConfig {

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new EnvironmentPropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(false);
        propertyPlaceholderConfigurer.setLocations(locations());
        return propertyPlaceholderConfigurer;
    }

    protected Resource[] locations() {
        return new ClassPathResource[]{new ClassPathResource("be.gerard.core.service.properties")};
    }

    @Bean
    public PasswordEncryptor passwordEncryptor() {
        return new StrongPasswordEncryptor();
    }

}
