package com.mycompany.webapp.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages = {"com.mycompany.webapp.dao.impl",
        "com.mycompany.webapp.services.impl", "com.mycompany.webapp.controllers"})
@EnableTransactionManagement
@Profile("dev")
public class BeanConfigDev {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryInMemoryDB() {
        LocalContainerEntityManagerFactoryBean thing = new LocalContainerEntityManagerFactoryBean();
        thing.setPersistenceUnitName("dev");
        return thing;
    }

    @Bean
    public JpaTransactionManager transactionManagerPostgresDB() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryInMemoryDB().getNativeEntityManagerFactory());
        return transactionManager;
    }
}
