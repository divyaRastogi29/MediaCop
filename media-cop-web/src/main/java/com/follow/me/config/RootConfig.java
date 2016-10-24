package com.follow.me.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;

/**
 * Created by divya on 20/10/16.
 */

@Configuration
@ComponentScan(basePackages = {"follow"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class))
@PropertySource(value = {"classpath:app.properties","classpath:db.properties"},ignoreResourceNotFound = false)
public class RootConfig {

    @Bean
    public DriverManagerDataSource getDriverManager(@Value("${jdbc.driverClassName}") String driver , @Value("${jdbc.url}") String url,
                                                    @Value("${jdbc.username}") String userName , @Value("${jdbc.password}") String password){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactoryBean(DriverManagerDataSource dataSource){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan("follow");
        Properties properties = new Properties();
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("show_sql","true");
        properties.setProperty("hibernate.hbm2ddl.auto","update");
        localSessionFactoryBean.setHibernateProperties(properties);
       localSessionFactoryBean.setAnnotatedClasses(com.follow.me.entity.HashTag.class) ;
        return localSessionFactoryBean ;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

}
