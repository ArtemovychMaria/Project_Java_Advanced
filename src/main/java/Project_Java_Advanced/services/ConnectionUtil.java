package Project_Java_Advanced.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static final String JDBC_URL="jdbc:mysql://localhost:3306/internet_shop";
    private static final String USER_NAME="root";
    private static final String PASSWORD="123456";

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL,USER_NAME,PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Can`t connect to DB");
        }
    }
}
