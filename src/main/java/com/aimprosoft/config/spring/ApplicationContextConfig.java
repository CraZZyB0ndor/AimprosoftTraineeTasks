package com.aimprosoft.config.spring;

import com.aimprosoft.config.HibernateSessionFactory;
import com.aimprosoft.utils.PropertiesUtils;
import com.aimprosoft.validation.OvalValidator;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.guard.GuardInterceptor;
import net.sf.oval.integration.spring.SpringCheckInitializationListener;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@ComponentScan({"com.aimprosoft", "net.sf.oval.integration.spring"})
@EnableTransactionManagement
public class ApplicationContextConfig {

    @Bean(name = "sessionFactory")
    public SessionFactory getDataSource() {
        return HibernateSessionFactory.getSessionFactory();
    }

    /*
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
     */

    @Bean(name = "ovalValidator")
    public Validator validator() {
        AnnotationsConfigurer annotationsConfigurer = new AnnotationsConfigurer();
        annotationsConfigurer.addCheckInitializationListener(SpringCheckInitializationListener.INSTANCE);
        return new Validator(annotationsConfigurer);
    }

    @Bean
    public OvalValidator<?> validator(@Qualifier("ovalValidator") Validator validator) {
        return new OvalValidator<>(validator);
    }

    @Bean
    public GuardInterceptor ovalGuardInterceptor() {
        return new GuardInterceptor();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setConnectionProperties(PropertiesUtils.getPropertiesByName("application.properties"));
        return source;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }
}
