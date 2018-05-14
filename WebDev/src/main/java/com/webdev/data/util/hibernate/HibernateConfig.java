package com.webdev.data.util.hibernate;

import java.util.Properties;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class HibernateConfig {

	public static final String DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
	
    @Autowired
    @Bean(name = "entityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean getEnttityManagerFactory(DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        entityManagerFactory.setPackagesToScan(new String[]{"com.webdev.data.model"});
        entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactory.setJpaProperties(hibernateJpaProperties());
        
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        
        entityManagerFactory.setJpaVendorAdapter(adapter);
        
        return entityManagerFactory;
    }

    private Properties hibernateJpaProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.dialect", System.getProperty("hibernate_dialect" , DIALECT));
     
    return properties;
    
    }

    @Autowired
    @Bean(name="jpaTransactionManager")
    public JpaTransactionManager getJpaTransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
         jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
         return jpaTransactionManager;
    }
}
