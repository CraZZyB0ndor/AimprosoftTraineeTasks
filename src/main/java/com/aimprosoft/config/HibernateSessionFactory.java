package com.aimprosoft.config;

import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

final public class HibernateSessionFactory {

    private static final SessionFactory factory;

    static {
        final Configuration configuration = new Configuration().configure();
        EntityScanner.scanPackages("com.aimprosoft.models")
                .addTo(configuration);
        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        factory = configuration.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    private HibernateSessionFactory() {}
}
