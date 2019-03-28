
import db.DBConnector;
import javafx.scene.layout.AnchorPane;
import jdk.nashorn.internal.runtime.Version;
import mysqlTablesSchema.TableModels;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    public static void main(String[] args) throws Exception {

        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;


        try {
                 connection = DBConnector.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(TableModels.EMPLOYEE_TABLE);
            statement.executeUpdate(TableModels.SUPPLIER_TABLE);

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
