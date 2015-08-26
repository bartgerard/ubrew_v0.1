package be.gerard.common.bootstrap.config;

import be.gerard.common.invoker.CustomHttpInvokerRequestExecutor;
import be.gerard.core.interface_v1.TranslationService;
import be.gerard.core.interface_v1.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * CoreWebConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
public class RemoteCoreServiceConfig {

    @Value("#{coreServiceUrl}")
    private String coreServiceUrl;

    @Bean
    public HttpInvokerProxyFactoryBean userService() {
        HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
        httpInvokerProxyFactoryBean.setServiceInterface(UserService.class);
        httpInvokerProxyFactoryBean.setServiceUrl(coreServiceUrl + "/remoting/userService_v1");
        httpInvokerProxyFactoryBean.setHttpInvokerRequestExecutor(customHttpInvokerRequestExecutor());
        return httpInvokerProxyFactoryBean;
    }

    @Bean
    public HttpInvokerProxyFactoryBean translationService() {
        HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
        httpInvokerProxyFactoryBean.setServiceInterface(TranslationService.class);
        httpInvokerProxyFactoryBean.setServiceUrl(coreServiceUrl + "/remoting/translationService_v1");
        httpInvokerProxyFactoryBean.setHttpInvokerRequestExecutor(customHttpInvokerRequestExecutor());
        return httpInvokerProxyFactoryBean;
    }

    @Bean
    public CustomHttpInvokerRequestExecutor customHttpInvokerRequestExecutor() {
        return new CustomHttpInvokerRequestExecutor();
    }

}
