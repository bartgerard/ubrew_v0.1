package be.gerard.core.service.schema;

import be.gerard.common.db.schema.SchemaExporter;
import org.hibernate.dialect.MySQLDialect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@ContextConfiguration(locations = {"classpath:be.gerard.core.service.test.xml"})
@Transactional
public class CoreSchemaExportTest extends SchemaExporter {

    @Autowired
    private LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;

    @Test
    public void export() throws ClassNotFoundException {
        export(MySQLDialect.class, "core", localContainerEntityManagerFactoryBean.getPersistenceUnitInfo().getManagedClassNames());
    }

}
