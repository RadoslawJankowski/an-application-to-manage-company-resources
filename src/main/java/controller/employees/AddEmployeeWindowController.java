package controller.employees;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static mysqlCommands.InsertIntoQuerys.*;
import static db.DBConnector.getConnection;

public class AddEmployeeWindowController implements Initializable {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;


    @FXML
    TextField firstNameTextField, lastNameTextField, birthDayDateTextField,
              sexTextField, positionTextField, salaryTextField;

    @FXML
    Button addEmployeeButton;

    public void addEmployeeButtonPushed() throws SQLException {

        connection = getConnection();
        statement = connection.createStatement();
            statement.executeUpdate(INSERT_INTO_EMPLOYEE_QUERY
                    + firstNameTextField.getText() + SEPARATE
                    + lastNameTextField.getText() + SEPARATE
                    + birthDayDateTextField.getText() + SEPARATE
                    + sexTextField.getText() + SEPARATE
                    + positionTextField.getText() + SEPARATE
                    + salaryTextField.getText()
                    + END_OF_INSERT_INTO_QUERY);
        JOptionPane.showMessageDialog(new Frame(), "Dodano nowego pracownika.");
        statement.close();
        connection.close();
    }

    public void initialize(URL location, ResourceBundle resources) {

        try {
            connection = getConnection();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
