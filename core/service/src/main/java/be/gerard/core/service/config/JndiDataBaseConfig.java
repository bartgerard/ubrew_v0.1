package be.gerard.core.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * DatabaseConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
@EnableJpaRepositories("be.gerard.core.service.dao")
@EnableTransactionManagement
public class JndiDataBaseConfig extends AbstractDataBaseConfig {

    @Bean
    public DataSource dataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        return dsLookup.getDataSource("jdbc/db.datasource.core");
    }

}
