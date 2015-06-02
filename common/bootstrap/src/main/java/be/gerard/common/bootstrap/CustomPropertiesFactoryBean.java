package be.gerard.common.bootstrap;

import be.gerard.core.interface_v1.AuthenticationService;
import be.gerard.core.interface_v1.session.AppSession;
import be.gerard.core.interface_v1.util.AppSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * CustomPropertiesFactoryBean
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class CustomPropertiesFactoryBean extends PropertiesFactoryBean {

    @Autowired
    private AuthenticationService authenticationService;

    @Value("#{applicationKey}")
    private String applicationKey;

    @Value("#{applicationReference}")
    private String applicationReference;

    @Value("#{applicationPassword}")
    private String applicationPassword;

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public void setApplicationKey(String applicationKey) {
        this.applicationKey = applicationKey;
    }

    public void setApplicationPassword(String applicationPassword) {
        this.applicationPassword = applicationPassword;
    }

    public String getApplicationPassword() {
        return applicationPassword;
    }

    @Override
    protected Properties createProperties() throws IOException {
        Properties properties = super.createProperties();
        AppSession appSession = authenticationService.register(applicationKey, applicationReference, applicationPassword);
        Assert.notNull(appSession, String.format("password invalid for application [%s]", applicationKey));
        AppSessionUtils.setAppSession(appSession);

        // ADD NEW APPLICATION SPECIFIC PROPERTIES TO PROPERTIES...
        for (Map.Entry<String, String> property : appSession.getProperties().entrySet()) {
            properties.put(property.getKey(), property.getValue());
        }


        return properties;
    }

}
