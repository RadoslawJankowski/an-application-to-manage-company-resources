package controller.employees;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import jdk.nashorn.internal.runtime.Version;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static controller.employees.EmployeeMainPanelController.getInstanceOfEmpMainPanel;
import static db.DBConnector.getConnection;
import static mysqlCommands.UpdateEmployeeQuery.UPDATE_EMPLOYEE;

public class UpdateEmployeeController implements Initializable {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;

    @FXML
    TextField firstNameTextFieldUpdate, lastNameTextFieldUpdate, birthDayDateTextFieldUpdate,
            sexTextFieldUpdate, positionTextFieldUpdate, salaryTextFieldUpdate;

    public void saveEmployeeUpdate() throws SQLException {

        int p = JOptionPane.showConfirmDialog(null,
                                              "Potwierdzasz zmiany?",
                                              "Edytor danych pracownika" + getInstanceOfEmpMainPanel().employeeToUpdate.getEmp_id(),
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
            getInstanceOfEmpMainPanel().selectAllEmployees();
        }
        else JOptionPane.showMessageDialog(new Frame(), "Podano błędny typ danych!");

                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }
    }

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
