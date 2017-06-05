package com.netcracker.store.persistence.config;

import com.netcracker.store.persistence.properties.DatasourceProperties;
import com.netcracker.store.persistence.properties.JpaProperties;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by A-one on 23.04.2017.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:persistence.properties")
@ComponentScan(basePackages = "com.netcracker.store.persistence")
public class DataConfig {
    private final DatasourceProperties datasourceProperties;
    private final JpaProperties jpaProperties;

    @Value("${entityPackages}")
    private String entityPackages;

    @Autowired
    public DataConfig(DatasourceProperties datasourceProperties, JpaProperties jpaProperties) {
        this.datasourceProperties = datasourceProperties;
        this.jpaProperties = jpaProperties;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(datasourceProperties.getDriverClassName());
        dataSource.setUrl(datasourceProperties.getUrl());
        dataSource.setUsername(datasourceProperties.getUsername());
        dataSource.setPassword(datasourceProperties.getPassword());
        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(entityPackages);
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getHibernateProperties());
        return em;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager getTransactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", jpaProperties.getShowSql());
        properties.put("spring.jpa.databasePlatform", jpaProperties.getDatabasePlatform());
        return properties;
    }
}
