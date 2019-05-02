package controller.suppliers;

import controller.employees.UpdateEmployeeController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static controller.suppliers.SupplierMainPanelController.getInstanceOfSuppMainPanel;
import static db.DBConnector.getConnectionToCompany;
import static mysqlCommands.updateQueries.suppliers.UpdateSuppliers.UPDATE_SUPPLIER;

/**
 * Controller for {@code UpdateSupplierWindow.fxml }.
 * <p>Methods:</p>
 * <li>{@link UpdateEmployeeController#saveEmployeeUpdate()}</li>
 * <li>{@link UpdateEmployeeController#initialize(URL, ResourceBundle)} </li>
 */
public class UpdateSupplierController implements Initializable {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;

    @FXML
    TextField nameTextFieldToUpdate, typeOfProductsTextFieldToUpdate, representativeTextFieldToUpdate, contactTextFieldToUpdate,
            cityTextFieldToUpdate, addressTextFieldToUpdate, emailTextFieldToUpdate;

    @FXML
    Button updateAndCloseButton;

    /**
     *
     * <p>Establishes a connection via {@link Connection } {@code connection = getConnectionToCompany ()}.</p>
     * <p>Prepares {@link PreparedStatement } {@code preparedStatement(UPDATE_SUPPLIER) } and sets {@code parameterIndex } values ​​to the
     * appropriate values ​​from {@link TextField} {@code nameTextFieldToUpdate, typeOfProductsTextFieldToUpdate} etc. </p>
     * If all data has been provided in the correct form, the user is asked to confirm, and then the user is added to the database.
     * Then it closes all connections and closes the windows.
     * If an incorrect data type is given, the user will be informed by the message "An incorrect data type was given!".
     * @throws SQLException
     */
    public void saveSupplierUpdate() throws SQLException {

        int p = JOptionPane.showConfirmDialog(null,
                "Potwierdzasz zmiany?",
                "Edytor danych dostawcy ID: " + getInstanceOfSuppMainPanel().supplierToUpdate.getSupplier_id(),
                JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            connection = getConnectionToCompany();
            statement = connection.prepareStatement(UPDATE_SUPPLIER);
            ((PreparedStatement) statement).setString(1, String.valueOf(nameTextFieldToUpdate.getText()));
            ((PreparedStatement) statement).setInt(2, Integer.parseInt(String.valueOf(typeOfProductsTextFieldToUpdate.getText())));
            ((PreparedStatement) statement).setString(3, representativeTextFieldToUpdate.getText());
            ((PreparedStatement) statement).setString(4, contactTextFieldToUpdate.getText());
            ((PreparedStatement) statement).setString(5, cityTextFieldToUpdate.getText());
            ((PreparedStatement) statement).setString(6, addressTextFieldToUpdate.getText());
            ((PreparedStatement) statement).setString(7, emailTextFieldToUpdate.getText());
            ((PreparedStatement) statement).setInt(8, getInstanceOfSuppMainPanel().supplierToUpdate.getSupplier_id());
            ((PreparedStatement) statement).executeUpdate();
        } else JOptionPane.showMessageDialog(new Frame(), "Podano błędny typ danych!");

        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }

        Stage stage = (Stage) updateAndCloseButton.getScene().getWindow();
        stage.close();
    }

    /**
     *
     * <p>In the initialization new fields {@code updateName, updateTypeOfProducts, updateRepresentative,
     *  updateContact, updateCity, updateAddress, updateEmail}
     *  are created whose values ​​are set to their respective values ​​from
     * {@code supplierToUpdate } from the {@link SupplierMainPanelController } class.</p>
     * Then sets the {@link TextField} values ​​to the values ​​of the newly created fields.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String updateName = getInstanceOfSuppMainPanel().getSupplierToUpdate().getName();
        int updateTypeOfProducts = getInstanceOfSuppMainPanel().getSupplierToUpdate().getType_of_products();
        String updateRepresentative = getInstanceOfSuppMainPanel().getSupplierToUpdate().getRepresentative();
        String updateContact = getInstanceOfSuppMainPanel().getSupplierToUpdate().getContact();
        String updateCity = getInstanceOfSuppMainPanel().getSupplierToUpdate().getCity();
        String updateAddress = getInstanceOfSuppMainPanel().getSupplierToUpdate().getAddress();
        String updateEmail = getInstanceOfSuppMainPanel().getSupplierToUpdate().getEmail();

        nameTextFieldToUpdate.setText(updateName);
        typeOfProductsTextFieldToUpdate.setText(String.valueOf(updateTypeOfProducts));
        representativeTextFieldToUpdate.setText(updateRepresentative);
        contactTextFieldToUpdate.setText(updateContact);
        cityTextFieldToUpdate.setText(updateCity);
        addressTextFieldToUpdate.setText(updateAddress);
        emailTextFieldToUpdate.setText(updateEmail);
    }
}
