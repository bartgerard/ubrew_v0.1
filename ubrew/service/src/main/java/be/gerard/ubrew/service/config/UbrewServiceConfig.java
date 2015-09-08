package be.gerard.ubrew.service.config;

import be.gerard.common.aop.config.AopConfig;
import be.gerard.common.converter.config.ConverterConfig;
import be.gerard.common.properties.EnvironmentPropertyPlaceholderConfigurer;
import be.gerard.common.validation.config.ValidationConfig;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
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
@Import({
        ConverterConfig.class,
        ValidationConfig.class,
        UbrewDatabaseConfig.class,
        UbrewDataSourceConfig.class,
        AopConfig.class
})
@ComponentScan(
        value = "be.gerard.ubrew.service",
        excludeFilters = @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION)
)
public class UbrewServiceConfig {

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new EnvironmentPropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(false);
        propertyPlaceholderConfigurer.setLocations(locations());
        return propertyPlaceholderConfigurer;
    }

    protected Resource[] locations() {
        return new ClassPathResource[]{new ClassPathResource("be.gerard.ubrew.service.properties")};
    }

}
