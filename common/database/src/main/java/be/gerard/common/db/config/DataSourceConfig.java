package be.gerard.common.db.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * DatasourceConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
public abstract class DataSourceConfig {

    @Bean
    protected abstract DataSource dataSource();

}
