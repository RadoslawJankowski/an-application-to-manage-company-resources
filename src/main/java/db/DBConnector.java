package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class that has a methods to connect to a MySQL database.
 * <p>Methods: </p>
 * <li>{@link DBConnector#getConnectionWithoutDatabase()}</li>
 * <li>{@link DBConnector#getConnectionToCompany()} </li>
 */
public class DBConnector {

    // There is probably a simple way to create a database and continue using it,
    // but I could not find it. In this simple way, I missed my problem.

    /**
     * The method used to connect to MySQL when we do not have our database yet
     * by {@link Connection} and {@link DriverManager} with url {@code jdbc:mysql://localhost/?useLegacyDatetimeCode=false&serverTimezone=UTC&" +
     *                 "user=root&password=database123}
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
     The method used to connect to MySQL when the "company" database already exists
     by {@link Connection} and {@link DriverManager} with url {@code jdbc:mysql://localhost/company?useLegacyDatetimeCode=false&serverTimezone=UTC&" +
    "user=root&password=database123}.
     * @return
     * @throws SQLException
     */
    public static Connection getConnectionToCompany() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/company?useLegacyDatetimeCode=false&serverTimezone=UTC&" +
                "user=root&password=database123");
        return connection;
    }

    /**
     *
     The method used to connect to MySQL when the "orders" database already exists
     by {@link Connection} and {@link DriverManager} with url {@code jdbc:mysql://localhost/orders?useLegacyDatetimeCode=false&serverTimezone=UTC&" +
    "user=root&password=database123}.
     * @return
     * @throws SQLException
     */
    public static Connection getConnectionToOrders() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/orders?useLegacyDatetimeCode=false&serverTimezone=UTC&" +
                "user=root&password=database123");
        return connection;
    }
}
