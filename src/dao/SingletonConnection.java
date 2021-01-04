package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SingletonConnection {
    private static Connection connection;
    static {
        try {
            Properties properties=new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("common.properties"));
            String driver=properties.getProperty("driver");
            String url=properties.getProperty("url");
            String username=properties.getProperty("username");
            String password=properties.getProperty("password");
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,"");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        return connection;
    }
}
