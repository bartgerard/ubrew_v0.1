package be.gerard.common.db.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * EmbeddedDataSourceConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class EmbeddedDataSourceConfig extends DataSourceConfig2 {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(embeddedDatabaseType())
                //.addScript("db/sql/create-db.sql")
                .build();
    }

    protected EmbeddedDatabaseType embeddedDatabaseType() {
        return EmbeddedDatabaseType.H2; //H2, HSQL or DERBY
    }

}
