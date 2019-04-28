package controller.suppliers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import static db.DBConnector.getConnection;
import static mysqlCommands.insertIntoQueries.suppliers.InsertIntoSuppliersQueries.INSERT_INTO_SUPPLIERS;

/**
 * Controller for {@code AddEmployeeWindow.fxml }.
 * <p>Methods: </p>
 * <li> {@link AddSupplierWindowController#addSupplierButtonPushed()}</li>
 */
public class AddSupplierWindowController {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;

    @FXML
    javafx.scene.control.TextField nameTextField, typeOfProductsTextField, representativeTextField,
              contactTextField, cityTextField,  addressTextField, emailTextField;

    @FXML
    Button addSupplierButton;

    /**
     *
     <p>The method establishes a connection through {@link Connection}
     connection = getConnection () and prepares statement = connection.prepareStatement (INSERT_INTO_SUPPLIERS).</p>
     <p>Gets data from the {@link TextField} and uses {@link PreparedStatement} to set values ​​to the query, respectively.</p>
     <p>Checks whether the given gender meets the requirements and then asks the user to confirm the operation.</p>
     If everything above is done, it closes all connections and closes the window of adding an supplier.
     Otherwise, it informs you that the data has been entered incorrectly.
     * @throws SQLException
     */
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

