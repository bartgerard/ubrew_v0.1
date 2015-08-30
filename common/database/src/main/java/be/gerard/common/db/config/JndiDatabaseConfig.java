package be.gerard.common.db.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

/**
 * DatabaseConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
public abstract class JndiDatabaseConfig extends AbstractDatabaseConfig {

    @Bean
    public DataSource dataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        return dsLookup.getDataSource(dataSourceName());
    }

    protected abstract String dataSourceName();

}
