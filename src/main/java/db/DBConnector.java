package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/firma?useLegacyDatetimeCode=false&serverTimezone=UTC&" +
                "user=root&password=database123");
        return connection;
    }
}
