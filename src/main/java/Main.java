
import db.DBConnector;
import javafx.scene.layout.AnchorPane;
import jdk.nashorn.internal.runtime.Version;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mysqlTablesSchema.ordersDatabaseTables.order_to_the_supplier.OrderToTheSupplier;

import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static mysqlCommands.CreateDatabases.CREATE_DATABASE_COMPANY;
import static mysqlCommands.CreateDatabases.CREATE_DATABASE_ORDERS;
import static mysqlCommands.insertIntoQueries.companyDatabase.employee_positions.InsertIntoEmploteePositionsQueries.BASIC_EMPLOYEE_POSITIONS;
import static mysqlCommands.insertIntoQueries.companyDatabase.suppliers_products.InsertIntoSuppliersProductsQueries.BASIC_SUPPLIERS_PRODUCTS;
import static mysqlCommands.insertIntoQueries.companyDatabase.types_or_products.InsertIntoTypesOfProductsQueries.BASIC_TYPES_OF_PRODUCTS;
import static mysqlTablesSchema.companyDatabaseTables.employee_positions.CreateEmployeePositionsTable.EMPLOYEE_POSITIONS_TABLE;
import static mysqlTablesSchema.companyDatabaseTables.employees.CreateEmployeesTable.EMPLOYEE_TABLE;
import static mysqlTablesSchema.ordersDatabaseTables.invoices_numbers.InvoicesNumbers.INVOICES_NUMBERS_TABLE;
import static mysqlTablesSchema.companyDatabaseTables.products.CreateProductsTable.PRODUCT_TABLE;
import static mysqlTablesSchema.companyDatabaseTables.suppliers.CreateSuppliersTable.SUPPLIER_TABLE;
import static mysqlTablesSchema.companyDatabaseTables.suppliers_products.CreateSuppliersProductsTable.SUPPLIERS_PRODUCTS_TABLE;
import static mysqlTablesSchema.companyDatabaseTables.types_of_products.CreateTypesOfProductsTable.TYPES_OF_PRODUCTS_TABLE;
import static mysqlTablesSchema.companyDatabaseTables.unfinished_order.CreateUnfinishedOrderTable.UNFINISHED_ORDER_TABLE;

public class Main extends Application {

    private static Object Result;


    public static void main(String[] args) {

        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        // creating database
        try {
            connection = DBConnector.getConnectionWithoutDatabase();
            statement = connection.createStatement();
            Result = statement.executeUpdate(CREATE_DATABASE_COMPANY);
            Result = statement.executeUpdate(CREATE_DATABASE_ORDERS);
            statement.close();
            connection.close();

            // creating tables
            connection = DBConnector.getConnectionToCompany();
            statement = connection.createStatement();
            statement.executeUpdate(EMPLOYEE_POSITIONS_TABLE);
            statement.executeUpdate(EMPLOYEE_TABLE);
            statement.executeUpdate(TYPES_OF_PRODUCTS_TABLE);
            statement.executeUpdate(SUPPLIER_TABLE);
            statement.executeUpdate(PRODUCT_TABLE);
            statement.executeUpdate(SUPPLIERS_PRODUCTS_TABLE);
            statement.executeUpdate(UNFINISHED_ORDER_TABLE);

            // insert products to type_of_products table
            statement = connection.prepareStatement(BASIC_TYPES_OF_PRODUCTS);
            ((PreparedStatement) statement).setString(1, "NABIAŁ");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "PIECZYWO");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "OWOCE");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "WARZYWA");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "MIĘSO");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "SŁODYCZE");
            ((PreparedStatement) statement).executeUpdate();

            // insert positions to employee_positions table
            statement = connection.prepareStatement(BASIC_EMPLOYEE_POSITIONS);
            ((PreparedStatement) statement).setString(1, "DYREKTOR");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "LOGISTYK");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "SEKRETARKA");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "ASYSTENTKA");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "KIEROWNIK");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "ZASTĘPCA KIEROWNIKA");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "OBSŁUGA");
            ((PreparedStatement) statement).executeUpdate();

            // insert products to suppliers_products table
            statement = connection.prepareStatement(BASIC_SUPPLIERS_PRODUCTS);
            ((PreparedStatement) statement).setString(1, "Mleko");
            ((PreparedStatement) statement).setInt(2, 1);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(2.80));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Jogurt");
            ((PreparedStatement) statement).setInt(2, 1);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(1.30));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Smietana");
            ((PreparedStatement) statement).setInt(2, 1);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(3.45));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Ser");
            ((PreparedStatement) statement).setInt(2, 1);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(8.90));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Kefir");
            ((PreparedStatement) statement).setInt(2, 1);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(1.75));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Chleb");
            ((PreparedStatement) statement).setInt(2, 2);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(2.40));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Bulka");
            ((PreparedStatement) statement).setInt(2, 2);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(0.60));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Rogalik");
            ((PreparedStatement) statement).setInt(2, 2);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(0.90));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Jablka");
            ((PreparedStatement) statement).setInt(2, 3);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(2.90));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Pomarancze");
            ((PreparedStatement) statement).setInt(2, 3);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(4.10));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Banany");
            ((PreparedStatement) statement).setInt(2, 3);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(3.60));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Ziemniaki");
            ((PreparedStatement) statement).setInt(2, 4);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(1.90));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Marchewka");
            ((PreparedStatement) statement).setInt(2, 4);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(1.70));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Cebula");
            ((PreparedStatement) statement).setInt(2, 4);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(0.90));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Kielbasa");
            ((PreparedStatement) statement).setInt(2, 5);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(12.80));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Szynka");
            ((PreparedStatement) statement).setInt(2, 5);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(19.20));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Parowki");
            ((PreparedStatement) statement).setInt(2, 5);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(8.80));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Schab");
            ((PreparedStatement) statement).setInt(2, 5);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(15.60));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Cukierki");
            ((PreparedStatement) statement).setInt(2, 6);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(24.70));
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1, "Lizak");
            ((PreparedStatement) statement).setInt(2, 6);
            ((PreparedStatement) statement).setBigDecimal(3, BigDecimal.valueOf(0.50));
            ((PreparedStatement) statement).executeUpdate();


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }

        try {
            connection = DBConnector.getConnectionToOrders();
            statement = connection.createStatement();
            statement.executeUpdate(INVOICES_NUMBERS_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        launch(args);
    }

    /**
     * Load first application window {@code MainMenuPanel.fxml}
     *
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxmlFiles/MainMenuPanel.fxml"));

        AnchorPane anchorPane = loader.load();

        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
