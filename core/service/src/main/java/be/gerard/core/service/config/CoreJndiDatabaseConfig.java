package be.gerard.core.service.config;

import be.gerard.common.db.config.JndiDatabaseConfig;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * DatabaseConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@EnableJpaRepositories("be.gerard.core.service.dao")
public class CoreJndiDatabaseConfig extends JndiDatabaseConfig {

    @Override
    protected String dataSourceName() {
        return "jdbc/db.datasource.core";
    }

    @Override
    protected String[] packagesToScan() {
        return new String[]{"be.gerard.core.service.model"};
    }

}
