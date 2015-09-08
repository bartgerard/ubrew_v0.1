package be.gerard.ubrew.core.service.config;

import be.gerard.common.db.config.CommonDatabaseConfig;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * DatabaseConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@EnableJpaRepositories("be.gerard.ubrew.core.service.dao")
public class UbrewCoreDatabaseConfig extends CommonDatabaseConfig {

    @Override
    protected String[] packagesToScan() {
        return new String[]{"be.gerard.ubrew.core.service.model"};
    }

}
