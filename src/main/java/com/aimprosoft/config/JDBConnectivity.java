package com.aimprosoft.config;

import com.aimprosoft.utils.PropertiesUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

final public class JDBConnectivity {

    public static Properties properties = PropertiesUtils.getPropertiesByName("database.properties");

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(properties.getProperty("db.driver.mySql"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(properties.getProperty("db.url"), properties);
    }

}
