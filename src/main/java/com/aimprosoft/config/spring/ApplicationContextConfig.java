package com.aimprosoft.config.spring;

import com.aimprosoft.config.HibernateSessionFactory;
import com.aimprosoft.models.Department;
import com.aimprosoft.models.Employee;
import com.aimprosoft.services.impl.CommandFactory;
import com.aimprosoft.utils.PropertiesUtils;
import com.aimprosoft.validation.OvalValidator;
import com.aimprosoft.validation.department.IsUniqueNameCheck;
import net.sf.oval.Validator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;


@Configuration
@ComponentScan("com.aimprosoft")
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

    @Bean
    public Validator getValidator() {
        return new Validator();
    }

    @Bean
    public OvalValidator<?> validator(@Qualifier("getValidator") Validator validator) {
        return new OvalValidator<>(validator);
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
