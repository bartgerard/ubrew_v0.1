package be.gerard.ubrew.service.config;

import be.gerard.common.db.config.JndiDataSourceConfig;

/**
 * DataSourceConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class UbrewDataSourceConfig extends JndiDataSourceConfig {

    @Override
    protected String dataSourceName() {
        return "jdbc/db.datasource.ubrew";
    }

}
