package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    // There is probably a simple way to create a database and continue using it,
    // but I could not find it. In this simple way, I missed my problem.

    /**
     * The method used to connect to MySQL when we do not have our database yet.
     * The method creates a database so the url does not have a database name.
     * @return
     * @throws SQLException
     */
    public static Connection getConnectionWithoutDatabase() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?useLegacyDatetimeCode=false&serverTimezone=UTC&" +
                "user=root&password=database123");
        return connection;
    }

    /**
     *
     The method used to connect to MySQL when the "company" database already exists.
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/company?useLegacyDatetimeCode=false&serverTimezone=UTC&" +
                "user=root&password=database123");
        return connection;
    }
}
