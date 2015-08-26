package be.gerard.core.config;

import be.gerard.common.bootstrap.CustomPropertiesFactoryBean;
import be.gerard.common.bootstrap.config.CommonBootstrapConfig;
import be.gerard.core.interface_v1.config.CoreInterfaceConfig;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * CoreWebConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
@ComponentScan({
        "be.gerard.core.web.controller",
        "be.gerard.common.web",
        "be.gerard.core.interface_v1.util",
        "be.gerard.common.security"
})
@Import({
        CommonBootstrapConfig.class,
        CoreInterfaceConfig.class
})
public class CoreWebConfig2 {

    @Bean
    public PropertiesFactoryBean propertiesFactoryBean() {
        CustomPropertiesFactoryBean propertiesFactoryBean = new CustomPropertiesFactoryBean();
        propertiesFactoryBean.setIgnoreResourceNotFound(false);
        propertiesFactoryBean.setLocalOverride(true);
        propertiesFactoryBean.setLocations(new ClassPathResource("be.gerard.core.web.properties"));
        return propertiesFactoryBean;
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
        propertyPlaceholderConfigurer.setIgnoreResourceNotFound(false);
        propertyPlaceholderConfigurer.setProperties(propertiesFactoryBean().getObject());
        // TODO
        return propertyPlaceholderConfigurer;
    }

}
