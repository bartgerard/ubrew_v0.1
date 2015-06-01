package be.gerard.common.bootstrap.config;

import be.gerard.core.interface_v1.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * CommonBootstrapConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
public class CommonBootstrapConfig {



    @Bean
    public HttpInvokerProxyFactoryBean authenticationService() {
        HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
        httpInvokerProxyFactoryBean.setServiceInterface(AuthenticationService.class);
        //httpInvokerProxyFactoryBean.setServiceUrl();
        return httpInvokerProxyFactoryBean;
    }

}
