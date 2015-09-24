package be.gerard.common.db.config;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * DatabaseConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@EnableTransactionManagement
public abstract class CommonDatabaseConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setDataSource(dataSource);
        factory.setPackagesToScan(packagesToScan());
        factory.setJpaProperties(jpaProperties());

        factory.afterPropertiesSet();

        return factory;
    }

    protected abstract String[] packagesToScan();

    protected Properties jpaProperties() {
        Properties properties = new Properties();

        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.GENERATE_STATISTICS, false);
        properties.put(Environment.SHOW_SQL, false);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
