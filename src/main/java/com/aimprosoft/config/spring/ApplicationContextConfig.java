package com.aimprosoft.config.spring;

import com.aimprosoft.config.HibernateSessionFactory;
import com.aimprosoft.controllers.MainController;
import com.aimprosoft.utils.PropertiesUtils;
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
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.util.Objects;


@Configuration
@ComponentScan({"com.aimprosoft", "net.sf.oval.integration.spring"})
@EnableTransactionManagement
public class ApplicationContextConfig {

    /*
    @Autowired
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.aimprosoft.models");

        sessionFactory.setHibernateProperties(Objects.
                requireNonNull(PropertiesUtils.getPropertiesByName("hibernate.properties")));


        return sessionFactory;
    }

     */


    /*
        Very important method for forwarding the Request from the Dispatcher Servlet to a Servlet
        then invoke the Servlet.service() method.
    */

    /*
    @Bean
    public SimpleServletHandlerAdapter simpleServletHandlerAdapter() {
        return new SimpleServletHandlerAdapter();
    }

    // Need for right servlet mapping.
    @Bean("/*")
    public MainController mainController() {
        return new MainController();
    }


     */
    @Bean(name = "sessionFactory")
    public SessionFactory getDataSource() {
        return HibernateSessionFactory.getSessionFactory();
    }


    /*
    @Autowired
    @Bean
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

     */

    /*
    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/pages/*");
        bean.setSuffix(".jsp");
        return bean;
    }

     */

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
