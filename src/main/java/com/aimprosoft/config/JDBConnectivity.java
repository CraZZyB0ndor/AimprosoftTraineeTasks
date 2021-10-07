package com.aimprosoft.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

final public class JDBConnectivity {

    public static Properties properties = new Properties();

    static {
        try {
            properties.load(
                    new FileInputStream(Objects.requireNonNull(
                            JDBConnectivity.class.getClassLoader().getResource("hibernate.properties")).getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(properties.getProperty("db.driver.mySql"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(properties.getProperty("db.url"), properties);
    }

}
