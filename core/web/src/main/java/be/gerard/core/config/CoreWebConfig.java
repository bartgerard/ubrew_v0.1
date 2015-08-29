package be.gerard.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * CoreWebConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
@ComponentScan({
        "be.gerard.common.logging",
        "be.gerard.common.bootstrap.config",
        "be.gerard.core.web.controller",
        "be.gerard.common.web",
        "be.gerard.core.interface_v1.util",
        "be.gerard.common.security.config"
})
@ImportResource("classpath:be.gerard.core.web.xml")
public class CoreWebConfig {
}
