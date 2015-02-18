package be.gerard.common.bootstrap;

import be.gerard.common.authentication.util.AppSessionUtils;
import be.gerard.general.interface_v1.ApplicationAuthenticationService;
import be.gerard.general.interface_v1.session.AppSession;
import java.io.IOException;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.util.Assert;

/**
 * CustomPropertiesFactoryBean
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class CustomPropertiesFactoryBean extends PropertiesFactoryBean {

    @Autowired
    private ApplicationAuthenticationService applicationAuthenticationService;

    @Value("#{applicationKey}")
    private String applicationKey;

    @Value("#{applicationPassword}")
    private String applicationPassword;

    public void setApplicationAuthenticationService(ApplicationAuthenticationService applicationAuthenticationService) {
        this.applicationAuthenticationService = applicationAuthenticationService;
    }

    public ApplicationAuthenticationService getApplicationAuthenticationService() {
        return applicationAuthenticationService;
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
        AppSession appSession = applicationAuthenticationService.register(applicationKey, applicationPassword);
        Assert.notNull(appSession, String.format("password invalid for application [%s]", applicationKey));
        AppSessionUtils.setAppSession(appSession);
        // TODO
        return properties;
    }

}
