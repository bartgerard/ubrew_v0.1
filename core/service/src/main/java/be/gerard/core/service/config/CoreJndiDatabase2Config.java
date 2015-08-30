package be.gerard.core.service.config;

import be.gerard.common.db.config.JndiDatabase2Config;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * DatabaseConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@EnableJpaRepositories("be.gerard.core.service.dao")
public class CoreJndiDatabase2Config extends JndiDatabase2Config {

    @Override
    protected String dataSourceName() {
        return "jdbc/db.datasource.core";
    }

    @Override
    protected String[] packagesToScan() {
        return new String[]{"be.gerard.core.service.model"};
    }

}
