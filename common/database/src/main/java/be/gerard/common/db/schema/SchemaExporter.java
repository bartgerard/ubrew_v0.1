package be.gerard.common.db.schema;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
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
        export(dialect, app_key, generateMetadata(dialect, defaultSchema, classNames));
    }

    protected MetadataSources generateMetadata(Class<? extends Dialect> dialect) throws ClassNotFoundException {
        return generateMetadata(dialect, null);
    }

    protected MetadataSources generateMetadata(Class<? extends Dialect> dialect, String defaultSchema) throws ClassNotFoundException {
        return generateMetadata(dialect, defaultSchema, null);
    }

    protected MetadataSources generateMetadata(Class<? extends Dialect> dialect, String defaultSchema, Collection<String> classNames) throws ClassNotFoundException {
        Assert.notNull(dialect, "dialect is invalid [null]");

        StandardServiceRegistryBuilder registryBuilder =
                new StandardServiceRegistryBuilder()
                        .applySetting(Environment.DIALECT, dialect.getName())
                        .applySetting(Environment.HBM2DDL_AUTO, "create-drop")
                        .applySetting(Environment.SHOW_SQL, "true")
                        .applySetting(Environment.FORMAT_SQL, "true")
                        .applySetting(Environment.USE_SQL_COMMENTS, "true")
                        .applySetting(Environment.ORDER_INSERTS, "true")
                        .applySetting(Environment.ORDER_UPDATES, "true");

        if (StringUtils.hasText(defaultSchema)) {
            registryBuilder.applySetting(Environment.DEFAULT_SCHEMA, defaultSchema);
        }

        MetadataSources metadata = new MetadataSources(registryBuilder.build());

        if (classNames != null) {
            for (String className : classNames) {
                metadata.addAnnotatedClass(Class.forName(className));
            }
        }

        return metadata;
    }

    protected void export(Class<? extends Dialect> dialect, String app_key, MetadataSources metadata) {
        export(dialect, app_key, metadata, true, true);
    }

    protected void export(Class<? extends Dialect> dialect, String app_key, MetadataSources metadata, boolean create, boolean drop) {
        Assert.notNull(dialect, "dialect is invalid [null]");
        Assert.notNull(app_key, "app_key is invalid [null]");
        Assert.notNull(metadata, "metadata is invalid [null]");

        SchemaExport schemaExport = new SchemaExport((MetadataImplementor) metadata.buildMetadata());
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
