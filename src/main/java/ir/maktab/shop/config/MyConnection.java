package ir.maktab.shop.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    private static Connection connection;

    static {
        try {
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres"
            ,"postgres","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
