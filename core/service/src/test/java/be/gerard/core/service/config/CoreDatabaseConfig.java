package be.gerard.core.service.config;

import be.gerard.common.db.config.PropertyDatabaseConfig;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * DatabaseConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@EnableJpaRepositories("be.gerard.core.service.dao")
public class CoreDatabaseConfig extends PropertyDatabaseConfig {

    @Override
    protected String[] packagesToScan() {
        return new String[]{"be.gerard.core.service.model"};
    }

}
