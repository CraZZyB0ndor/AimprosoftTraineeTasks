package com.aimprosoft.utils;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtils {

    public static Properties getPropertiesByName(String name) {
        final Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(name));
            return properties;
        } catch (IOException e) {
            return null;
        }
    }
}
