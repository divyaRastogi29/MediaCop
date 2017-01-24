package com.follow.me.config;


import com.follow.me.entity.HashTagDO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;

/**
 * Created by divya on 20/10/16.
 */

@Configuration
@ComponentScan(basePackages = {"com.follow.me"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class))
@PropertySource(value = {"classpath:app.properties","classpath:db.properties"},ignoreResourceNotFound = false)
@EnableTransactionManagement
public class RootConfig {

    @Bean
    public DriverManagerDataSource getDriverManager(@Value("${jdbc.driverClassName}") String driver , @Value("${jdbc.url}") String url,
                                                  @Value("${jdbc.username}") String userName , @Value("${jdbc.password}") String password){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
       /*
        dataSource.setMaxPoolSize(50);
        dataSource.setMinPoolSize(1);
        dataSource.setMaxStatements(20);
        dataSource.setTestConnectionOnCheckout(true);*/
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactoryBean(DriverManagerDataSource dataSource){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan("com.follow.me");
        Properties properties = new Properties();
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("show_sql","true");
        properties.setProperty("hibernate.hbm2ddl.auto","update");
        properties.setProperty("connection.pool_size","10");
        localSessionFactoryBean.setHibernateProperties(properties);
       localSessionFactoryBean.setAnnotatedClasses(HashTagDO.class) ;
        return localSessionFactoryBean ;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }

}
