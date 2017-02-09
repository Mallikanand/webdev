package com.webdev.data.util.hibernate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class HibernateConfig {

    @Autowired
    @Bean(name = "entityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean getEnttityManagerFactory(DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        bean.setPackagesToScan(new String[]{"com.webdev.data.model"});
        
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        bean.setJpaVendorAdapter(adapter);
        
        return bean;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {

        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.setProperty("hibernate.show_sql", "true");
        sessionBuilder.addPackages("com.webdev.data.model");

        return sessionBuilder.buildSessionFactory();
    }

    /*    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {

        SessionFactory sessionFactory;

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                //.configure( "org/hibernate/example/hibernate.cfg.xml" )
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClassName("com.webdev.data.model")
                .getMetadataBuilder()
                .build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        return sessionFactory;
    }
     */
    @Autowired
    @Bean(name = "hibernateTransactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager tranManager = new HibernateTransactionManager(sessionFactory);

        return tranManager;
    }
    
    @Autowired
    @Bean(name="jpaTransactionManager")
    public JpaTransactionManager getJpaTransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
         jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
         return jpaTransactionManager;
    }
}