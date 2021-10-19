package com.aimprosoft.config.spring;

import com.aimprosoft.validation.OvalValidator;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.guard.GuardInterceptor;
import net.sf.oval.integration.spring.SpringCheckInitializationListener;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;

import javax.sql.DataSource;


@Configuration
@ComponentScan({"com.aimprosoft", "net.sf.oval.integration.spring"})
@EnableWebMvc
public class ApplicationContextConfig {

    @Autowired
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.aimprosoft.models");
        return sessionFactory;
    }

    /*
        Important method for forwarding the Request from the Dispatcher Servlet to a Servlet
        then invoke the Servlet.service() method.
    */
    @Bean
    public SimpleServletHandlerAdapter simpleServletHandlerAdapter() {
        return new SimpleServletHandlerAdapter();
    }

    @Autowired
    @Bean
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean(name = "ovalValidator")
    public Validator validator() {
        AnnotationsConfigurer annotationsConfigurer = new AnnotationsConfigurer();
        annotationsConfigurer.addCheckInitializationListener(SpringCheckInitializationListener.INSTANCE);
        return new Validator(annotationsConfigurer);
    }

    @Bean(name = "validator")
    public OvalValidator<?> ovalValidator(@Qualifier("ovalValidator") Validator validator) {
        return new OvalValidator<>(validator);
    }

    @Bean
    public GuardInterceptor ovalGuardInterceptor() {
        return new GuardInterceptor();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/aimprosoftDbFirst");
        source.setUsername("root");
        source.setPassword("11111111");
        return source;
    }
}