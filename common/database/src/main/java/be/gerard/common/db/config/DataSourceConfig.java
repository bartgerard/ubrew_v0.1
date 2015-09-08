package be.gerard.common.db.config;

import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * DatasourceConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
public abstract class DataSourceConfig {

    @Bean
    protected abstract DataSource dataSource();

}
