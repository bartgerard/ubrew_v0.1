package be.gerard.core.service.config;

import be.gerard.common.db.config.JndiDataSourceConfig;

/**
 * DatabaseConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class CoreJndiDataSourceConfig extends JndiDataSourceConfig {

    @Override
    protected String dataSourceName() {
        return "jdbc/db.datasource.core";
    }

}
