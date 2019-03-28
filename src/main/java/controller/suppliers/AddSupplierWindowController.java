package controller.suppliers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

import static db.DBConnector.getConnection;
import static mysqlCommands.employeeCommands.InsertIntoTableQuerys.INSERT_INTO_SUPPLIERS;

public class AddSupplierWindowController {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;

    @FXML
    javafx.scene.control.TextField nameTextField, typeOfProductsTextField, representativeTextField,
              contactTextField, cityTextField,  addressTextField, emailTextField;

    @FXML
    Button addSupplierButton;

    public void addSupplierButtonPushed() throws SQLException {
        int p = JOptionPane.showConfirmDialog(null, "Czy potwierdzasz dodanie?", "Dodawanie dostawcy", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_INTO_SUPPLIERS);
            ((PreparedStatement) statement).setString(1, nameTextField.getText());
            ((PreparedStatement) statement).setInt(2, Integer.parseInt(typeOfProductsTextField.getText()));
            ((PreparedStatement) statement).setString(3, representativeTextField.getText());
            ((PreparedStatement) statement).setString(4, contactTextField.getText());
            ((PreparedStatement) statement).setString(5, cityTextField.getText());
            ((PreparedStatement) statement).setString(6, addressTextField.getText());
            ((PreparedStatement) statement).setString(7, emailTextField.getText());
            ((PreparedStatement) statement).executeUpdate();
            JOptionPane.showMessageDialog(new Frame(), "Dodano nowego dostawcę.");

            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }

            Stage stage = (Stage) addSupplierButton.getScene().getWindow();
            stage.close();
        }
    }
}

