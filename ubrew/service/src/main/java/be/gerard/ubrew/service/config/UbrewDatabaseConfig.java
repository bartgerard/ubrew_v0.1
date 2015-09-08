package be.gerard.ubrew.service.config;

import be.gerard.common.db.config.CommonDatabaseConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * DatabaseConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
@EnableJpaRepositories("be.gerard.ubrew.service.dao")
public class UbrewDatabaseConfig extends CommonDatabaseConfig {

    @Override
    protected String[] packagesToScan() {
        return new String[]{"be.gerard.ubrew.service.model"};
    }

}
