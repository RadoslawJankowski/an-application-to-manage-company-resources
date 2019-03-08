
import mysqlTableSchema.TableModels;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    public static void main(String[] args) throws Exception {

        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;


        try {
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost/firma?useLegacyDatetimeCode=false&serverTimezone=UTC&" +
                            "user=root&password=database123");
            statement = connection.createStatement();
            statement.executeUpdate(TableModels.EMPLOYEE_TABLE);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    finally {
        try { resultSet.close(); } catch (Exception e) { /* ignored */ }
        try { statement.close(); } catch (Exception e) { /* ignored */ }
        try { connection.close(); } catch (Exception e) { /* ignored */ }
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
