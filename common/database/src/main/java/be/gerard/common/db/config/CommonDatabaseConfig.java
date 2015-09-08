package be.gerard.common.db.config;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Configuration
@EnableTransactionManagement
public abstract class CommonDatabaseConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return localContainerEntityManagerFactoryBean().getObject();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
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
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

}
