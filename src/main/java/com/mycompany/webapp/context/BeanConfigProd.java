package com.mycompany.webapp.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"com.mycompany.webapp.dao.impl",
        "com.mycompany.webapp.services.impl", "com.mycompany.webapp.controllers"})
@EnableWebMvc
@EnableTransactionManagement
@Profile("prod")
public class BeanConfigProd {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPostgres() {
        LocalContainerEntityManagerFactoryBean thing = new LocalContainerEntityManagerFactoryBean();
        thing.setPersistenceUnitName("prod");
        return thing;
    }

    @Bean
    public JpaTransactionManager transactionManagerInMemoryDB()
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryPostgres().getNativeEntityManagerFactory());
        return transactionManager;
    }

}
