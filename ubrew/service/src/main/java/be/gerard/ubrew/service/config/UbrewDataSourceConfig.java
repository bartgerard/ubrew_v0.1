package be.gerard.ubrew.service.config;

import be.gerard.common.db.config.JndiDataSourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * DataSourceConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
public class UbrewDataSourceConfig extends JndiDataSourceConfig {

    @Override
    protected String dataSourceName() {
        return "jdbc/db.datasource.ubrew";
    }

}
