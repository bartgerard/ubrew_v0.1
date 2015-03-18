package be.gerard.common.properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * EnvironmentPropertyPlaceholderConfigurer
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class EnvironmentPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static final String ENVIRONMENT_PROPERTY = "ENV";

    private static final String ENVIRONMENT_DEFAULT = "DEV";

    @Override
    protected String resolvePlaceholder(final String placeholder, final Properties properties) {
        // First retrieve the environment from the system properties.
        String environment = getEnvironmentProperty();

        // If none existing, search for an environment property in the properties.
        if (environment == null) {
            environment = properties.getProperty(ENVIRONMENT_PROPERTY, ENVIRONMENT_DEFAULT);
        }

        // Check if there is an environment specific value.
        String value = super.resolvePlaceholder(environment + '.' + placeholder, properties);

        // If none existing, search for non environment specific value.
        if (value == null) {
            value = super.resolvePlaceholder(placeholder, properties);
        }

        // TODO Encryption?

        return value;
    }

    public String getEnvironmentProperty() {
        return System.getProperty("be.gerard.env");
    }

}
