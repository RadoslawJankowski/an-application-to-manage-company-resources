package controller.employees;

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

import static controller.employees.EmployeeMainPanelController.getInstanceOfEmpMainPanel;
import static db.DBConnector.getConnection;
import static mysqlCommands.deleteQueries.employees.DeleteFromEmployeesQueries.DELETE_EMPLOYEE_BY_ID;

/**
 * Controller for the {@code DeleteEmployee.fxml } window.
 * Methods:
 * <li> {@link DeleteEmployeeController#acceptDeleteEmployeeButtonPushed()}</li>
 */
public class DeleteEmployeeController {

    Statement statement;

    Connection connection;

    @FXML
    TextField empIdToDelete;

    @FXML
    Button acceptDelete;

    /**
     *<p>The method checks whether the data from {@link TextField}.{@code empIdToDelete } is equal to {@code employeToDelete.getEmpId()},
     * which has been downgraded in the {@code deleteSelectedEmployee()} from {@link EmployeeMainPanelController}.</p>
     * <p>If the values ​​are equal then it establishes the connection and prepares the statement</p>
     * and sets the id entered by {@code deleteSelectedEmployee()} as a value in the command.
     * Closes all connections and informs about the correct removal of the employee and closes the window.
     * In other cases, it informs about the wrong id
     */
    public void acceptDeleteEmployeeButtonPushed() {

        int p = JOptionPane.showConfirmDialog(null,
                "Potwierdzasz usunięcie?",
                "Usunięcie pracownika",
                JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            if (empIdToDelete.getText().contains(String.valueOf(getInstanceOfEmpMainPanel().employeeToDelete.getEmp_id()))) {
                try {
                    connection = getConnection();
                    statement = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID);
                    ((PreparedStatement) statement).setInt(1, getInstanceOfEmpMainPanel().employeeToDelete.getEmp_id());
                    ((PreparedStatement) statement).executeUpdate();

                    JOptionPane.showMessageDialog(new Frame(), "Pracownik został usunięty z bazy danych");
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
