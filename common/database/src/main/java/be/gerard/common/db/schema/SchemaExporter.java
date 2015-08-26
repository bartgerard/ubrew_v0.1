package be.gerard.common.db.schema;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.Dialect;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * SchemaExporter
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class SchemaExporter {
    
    
    public void export(Class<? extends Dialect> dialect, String app_key, Collection<String> classNames) throws ClassNotFoundException {
        export(dialect, app_key, null, classNames);
    }

    public void export(Class<? extends Dialect> dialect, String app_key, String defaultSchema, Collection<String> classNames) throws ClassNotFoundException {
        export(dialect, app_key, generateConfiguration(dialect, defaultSchema, classNames));
    }

    protected Configuration generateConfiguration(Class<? extends Dialect> dialect) throws ClassNotFoundException {
        return generateConfiguration(dialect, null);
    }

    protected Configuration generateConfiguration(Class<? extends Dialect> dialect, String defaultSchema) throws ClassNotFoundException {
        return generateConfiguration(dialect, defaultSchema, null);
    }

    protected Configuration generateConfiguration(Class<? extends Dialect> dialect, String defaultSchema, Collection<String> classNames) throws ClassNotFoundException {
        Assert.notNull(dialect, "dialect is invalid [null]");
        
        Configuration configuration = new Configuration()
                .setProperty(Environment.DIALECT, dialect.getName())
                .setProperty(Environment.HBM2DDL_AUTO, "create-drop")
                .setProperty(Environment.SHOW_SQL, "true")
                .setProperty(Environment.FORMAT_SQL, "true")
                .setProperty(Environment.USE_SQL_COMMENTS, "true")
                .setProperty(Environment.ORDER_INSERTS, "true")
                .setProperty(Environment.ORDER_UPDATES, "true");

        if (StringUtils.hasText(defaultSchema)) {
            configuration.setProperty(Environment.DEFAULT_SCHEMA, defaultSchema);
        }

        if (classNames != null) {
            for (String className : classNames) {
                configuration.addAnnotatedClass(Class.forName(className));
            }
            // Add Package DOES NOT WORK
//        configuration
//                .addAnnotatedClass(UserRecord.class)
//                .addAnnotatedClass(ApplicationRecord.class)
//                .addAnnotatedClass(SimpleBaseRecord.class)
//                .addAnnotatedClass(BaseRecord.class);
        }

        return configuration;
    }

    protected void export(Class<? extends Dialect> dialect, String app_key, Configuration configuration) {
        export(dialect, app_key, configuration, true, true);
    }

    protected void export(Class<? extends Dialect> dialect, String app_key, Configuration configuration, boolean create, boolean drop) {
        Assert.notNull(dialect, "dialect is invalid [null]");
        Assert.notNull(app_key, "app_key is invalid [null]");
        Assert.notNull(configuration, "configuration is invalid [null]");
        
        SchemaExport schemaExport = new SchemaExport(configuration);
        schemaExport.setDelimiter(";");
        schemaExport.setFormat(true);

        if (create) {
            // Generate create script
            schemaExport.setOutputFile(String.format("target/%s_ddl_%s_create.sql", app_key, dialect.getSimpleName()));
            schemaExport.execute(true, false, false, true);
        }

        if (drop) {
            // Generate drop script
            schemaExport.setOutputFile(String.format("target/%s_ddl_%s_drop.sql", app_key, dialect.getSimpleName()));
            schemaExport.execute(true, false, true, false);
        }
    }

}
