import db.DBConnector;
import javafx.scene.layout.AnchorPane;
import jdk.nashorn.internal.runtime.Version;
import mysqlTablesSchema.TablesModels;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static mysqlCommands.CreateDatabase.CREATE_DATABASE;
import static mysqlCommands.InsertIntoTableQueries.BASIC_EMPLOYEE_POSITIONS;
import static mysqlCommands.InsertIntoTableQueries.BASIC_TYPES_OF_PRODUCTS;

public class Main extends Application {

    private static Object Result;

    public static void main(String[] args) {

        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try {
                 connection = DBConnector.getConnectionWithoutDatabase();
            statement = connection.createStatement();
            Result = statement.executeUpdate(CREATE_DATABASE);
            statement.close();
            connection.close();

            connection = DBConnector.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(TablesModels.EMPLOYEE_POSITIONS);
            statement.executeUpdate(TablesModels.EMPLOYEE_TABLE);
            statement.executeUpdate(TablesModels.TYPES_OF_PRODUCTS);
            statement.executeUpdate(TablesModels.SUPPLIER_TABLE);
            statement.executeUpdate(TablesModels.PRODUCT_TABLE);
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

            statement = connection.prepareStatement(BASIC_EMPLOYEE_POSITIONS);
            ((PreparedStatement) statement).setString(1 , "DYREKTOR");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1 , "LOGISTYK");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1 , "SEKRETARKA");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1 , "ASYSTENTKA");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1 , "KIEROWNIK");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1 , "ZASTĘPCA KIEROWNIKA");
            ((PreparedStatement) statement).executeUpdate();
            ((PreparedStatement) statement).setString(1 , "OBSŁUGA");
            ((PreparedStatement) statement).executeUpdate();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }

        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxmlFiles/MainMenuPanel.fxml"));

        AnchorPane anchorPane = loader.load();

        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
