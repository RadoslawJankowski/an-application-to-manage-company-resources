package controller.suppliers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static controller.suppliers.SupplierMainPanelController.getInstanceOfSuppMainPanel;
import static db.DBConnector.getConnection;
import static mysqlCommands.DeleteQueries.DELETE_SUPPLIER_BY_ID;

public class DeleteSupplierController {

    Statement statement;

    Connection connection;

    @FXML
    TextField supplierIdToDelete;

    @FXML
    Button acceptDelete;

    /**
     *<p>The method checks whether the data from {@link TextField}.{@code supplierIdToDelete } is equal to {@code supplierToDelete.getSupplierId()},
     * which has been downgraded in the {@code deleteSelectedSupplier()} from {@link SupplierMainPanelController}.</p>
     * <p>If the values ​​are equal then it establishes the connection and prepares the statement</p>
     * and sets the id entered by {@code deleteSelectedSupplier()} as a value in the command.
     * Closes all connections and informs about the correct removal of the employee and closes the window.
     * In other cases, it informs about the wrong id
     */
    public void acceptDeleteSupplierButtonPushed() {

        int p = JOptionPane.showConfirmDialog(null,
                "Potwierdzasz usunięcie?",
                "Usuwanie dostawcy",
                JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            if (supplierIdToDelete.getText().contains(String.valueOf(getInstanceOfSuppMainPanel().supplierToDelete.getSupplier_id()))) {
                try {
                    connection = getConnection();
                    statement = connection.prepareStatement(DELETE_SUPPLIER_BY_ID);
                    ((PreparedStatement) statement).setInt(1, getInstanceOfSuppMainPanel().supplierToDelete.getSupplier_id());
                    ((PreparedStatement) statement).executeUpdate();

                    JOptionPane.showMessageDialog(new Frame(), "Dostawca został usunięty z bazy danych");
                } catch (SQLException e) {
                    System.out.println(e);

                    try {
                        statement.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        connection.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            else JOptionPane.showMessageDialog(new Frame(), "Podano błędny numer ID!");
        }

        Stage stage = (Stage) acceptDelete.getScene().getWindow();
        stage.close();
    }
}
