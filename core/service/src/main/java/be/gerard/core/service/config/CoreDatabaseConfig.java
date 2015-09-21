package be.gerard.core.service.config;

import be.gerard.common.db.config.CommonDatabaseConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * CoreDatabaseConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
@EnableJpaRepositories(basePackages = "be.gerard.core.service.dao")
public class CoreDatabaseConfig extends CommonDatabaseConfig {

    @Override
    protected String[] packagesToScan() {
        return new String[]{"be.gerard.core.service.model"};
    }

}
