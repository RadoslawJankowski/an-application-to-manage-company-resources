package controller.employees;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static controller.employees.EmployeeMainPanelController.getInstanceOfEmpMainPanel;
import static db.DBConnector.getConnection;
import static mysqlCommands.UpdateQueries.UPDATE_EMPLOYEE;

public class UpdateEmployeeController implements Initializable {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;

    @FXML
    TextField firstNameTextFieldUpdate, lastNameTextFieldUpdate, birthDayDateTextFieldUpdate,
            sexTextFieldUpdate, positionTextFieldUpdate, salaryTextFieldUpdate;

    @FXML
    Button updateAndCloseButton;

    /**
     *
     * <p>Establishes a connection via {@link Connection } {@code connection = getConnection ()}.</p>
     * <p>Prepares {@link PreparedStatement } {@code preparedStatement(UPDATE_EMPLOYEE) } and sets {@code parameterIndex } values ​​to the
     * appropriate values ​​from {@link TextField} {@code firstNameTextFieldUpdate, lastNameTextFieldUpdate} etc. </p>
     * If all data has been provided in the correct form, the user is asked to confirm, and then the user is added to the database.
     * Then it closes all connections and closes the windows.
     * If an incorrect data type is given, the user will be informed by the message "An incorrect data type was given!".
     * @throws SQLException
     */
    public void saveEmployeeUpdate() throws SQLException {

        int p = JOptionPane.showConfirmDialog(null,
                "Potwierdzasz zmiany?",
                "Edytor danych pracownika ID: " + getInstanceOfEmpMainPanel().employeeToUpdate.getEmp_id(),
                JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            connection = getConnection();
            statement = connection.prepareStatement(UPDATE_EMPLOYEE);
            ((PreparedStatement) statement).setString(1, String.valueOf(firstNameTextFieldUpdate.getText()));
            ((PreparedStatement) statement).setString(2, String.valueOf(lastNameTextFieldUpdate.getText()));
            ((PreparedStatement) statement).setDate(3, Date.valueOf(birthDayDateTextFieldUpdate.getText()));
            ((PreparedStatement) statement).setString(4, sexTextFieldUpdate.getText());
            ((PreparedStatement) statement).setInt(5, Integer.parseInt(positionTextFieldUpdate.getText()));
            ((PreparedStatement) statement).setBigDecimal(6, BigDecimal.valueOf(Double.parseDouble(salaryTextFieldUpdate.getText())));
            ((PreparedStatement) statement).setInt(7, getInstanceOfEmpMainPanel().employeeToUpdate.getEmp_id());
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
     * <p>In the initialization new fields {@code updateName, updateLastName, updateBirthdDayDate, upgradeSex, updatePosition} and
     * {@code updateSalary} are created whose values ​​are set to their respective values ​​from
     * {@code employeeToUpdate } from the {@link EmployeeMainPanelController } class.</p>
     * Then sets the {@link TextField} values ​​to the values ​​of the newly created fields.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String updateName = getInstanceOfEmpMainPanel().getEmployeeToUpdate().getFirstName();
        String updateLastName = getInstanceOfEmpMainPanel().getEmployeeToUpdate().getLastName();
        Date updateBirthDayDate = (Date) getInstanceOfEmpMainPanel().getEmployeeToUpdate().getBirthDayDate();
        String updateSex = getInstanceOfEmpMainPanel().getEmployeeToUpdate().getSex();
        int updatePosition = getInstanceOfEmpMainPanel().getEmployeeToUpdate().getEmpPosition();
        BigDecimal updateSalary = getInstanceOfEmpMainPanel().getEmployeeToUpdate().getSalary();

        firstNameTextFieldUpdate.setText(updateName);
        lastNameTextFieldUpdate.setText(updateLastName);
        birthDayDateTextFieldUpdate.setText(String.valueOf(updateBirthDayDate));
        sexTextFieldUpdate.setText(updateSex);
        positionTextFieldUpdate.setText(String.valueOf(updatePosition));
        salaryTextFieldUpdate.setText(String.valueOf(updateSalary));

    }
}
