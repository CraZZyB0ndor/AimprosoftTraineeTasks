package com.aimprosoft.config;

import com.aimprosoft.models.Department;
import com.aimprosoft.models.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

final public class HibernateSessionFactory {

    private static final SessionFactory factory;

    static {
        final Configuration configuration = new Configuration().configure();
        /*
        EntityScanner.scanPackages("com.aimprosoft.models")
              .addTo(configuration);
        */
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Employee.class);
        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        factory = configuration.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    private HibernateSessionFactory() {
    }
}
