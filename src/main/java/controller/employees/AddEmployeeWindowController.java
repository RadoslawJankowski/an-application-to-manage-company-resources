package controller.employees;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;

import static db.DBConnector.getConnection;
import static mysqlCommands.insertIntoQueries.employees.InsertIntoEmployeesQueries.INSERT_INTO_EMPLOYEES;

/**
 * Controller for the {@code AddEmployeeWindow.fxml } window.
 * Methods:
 * <li> {@link AddEmployeeWindowController#addEmployeeButtonPushed()}</li>
 */
public class AddEmployeeWindowController {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;


    @FXML
    TextField firstNameTextField, lastNameTextField, birthDayDateTextField,
            sexTextField, positionTextField, salaryTextField;

    @FXML
    Button addEmployeeButton;

    /**
     *
     <p>The method establishes a connection through {@link Connection}
     connection = getConnection () and prepares statement = connection.prepareStatement (INSERT_INTO_EMPLOYEES).</p>
     <p>Gets data from the {@link TextField} and uses {@link PreparedStatement} to set values ​​to the query, respectively.</p>
     <p>Checks whether the given gender meets the requirements and then asks the user to confirm the operation.</p>
     If everything above is done, it closes all connections and closes the window of adding an employee.
     Otherwise, it informs you that the data has been entered incorrectly.
     * @throws SQLException
     */
    public void addEmployeeButtonPushed() throws SQLException {

        if (sexTextField.getText().toUpperCase().contentEquals("F") || sexTextField.getText().toUpperCase().contentEquals("M")) {
            int p = JOptionPane.showConfirmDialog(null, "Czy potwierdzasz dodanie pracownika?", "Dodawanie pracownika", JOptionPane.YES_NO_OPTION);
            if (p == 0) {
                connection = getConnection();
                statement = connection.prepareStatement(INSERT_INTO_EMPLOYEES);
                ((PreparedStatement) statement).setString(1, firstNameTextField.getText());
                ((PreparedStatement) statement).setString(2, lastNameTextField.getText());
                ((PreparedStatement) statement).setDate(3, Date.valueOf(birthDayDateTextField.getText()));
                ((PreparedStatement) statement).setString(4, sexTextField.getText());
                ((PreparedStatement) statement).setInt(5, Integer.parseInt(positionTextField.getText()));
                ((PreparedStatement) statement).setBigDecimal(6, BigDecimal.valueOf(Double.parseDouble(salaryTextField.getText())));
                ((PreparedStatement) statement).executeUpdate();
                JOptionPane.showMessageDialog(new Frame(), "Dodano nowego pracownika.");

                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }

                Stage stage = (Stage) addEmployeeButton.getScene().getWindow();
                stage.close();
            }
        }
        else JOptionPane.showMessageDialog(new Frame(), "Podano błędny typ danych!");
    }
}
