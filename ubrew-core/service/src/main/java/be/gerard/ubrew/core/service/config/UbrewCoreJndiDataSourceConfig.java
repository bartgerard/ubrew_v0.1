package be.gerard.ubrew.core.service.config;

import be.gerard.common.db.config.JndiDataSourceConfig;

/**
 * UbrewCoreJndiDataSourceConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class UbrewCoreJndiDataSourceConfig extends JndiDataSourceConfig {

    @Override
    protected String dataSourceName() {
        return "jdbc/db.ubrew.core.datasource";
    }

}
