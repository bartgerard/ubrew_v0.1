package be.gerard.ubrew.core.service.schema;

import be.gerard.common.db.schema.SchemaExporter;
import be.gerard.ubrew.core.service.config.UbrewCoreDatabaseConfig;
import be.gerard.ubrew.core.service.config.UbrewCoreServiceConfig;
import org.hibernate.dialect.MySQLDialect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * CoreAuthenticationSchemaExportTest
 *
 * @author bartgerard
 * @version 0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UbrewCoreDatabaseConfig.class})
@Transactional
public class UbrewCoreSchemaExportTest extends SchemaExporter {

    @Autowired
    private LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;

    @Test
    public void export() throws ClassNotFoundException {
        export(MySQLDialect.class, "ubrew-core", localContainerEntityManagerFactoryBean.getPersistenceUnitInfo().getManagedClassNames());
    }

}
