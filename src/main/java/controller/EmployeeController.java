package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static connector.TableModels.*;
public class EmployeeController implements Initializable {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;
    @FXML
    TextField firstNameTextField, lastNameTextField, birthDayDateTextField, sexTextField, positionTextField, salaryTextField;

    public void addEmployeeButtonPushed(ActionEvent event) throws SQLException {

        statement = connection.createStatement();
            statement.executeUpdate(INSERT_INTO_EMPLOYEE_QUERY
                    + firstNameTextField.getText() + SEPARATE
                    + lastNameTextField.getText() + SEPARATE
                    + birthDayDateTextField.getText() + SEPARATE
                    + sexTextField.getText() + SEPARATE
                    + positionTextField.getText() + SEPARATE
                    + salaryTextField.getText()
                    + END_OF_EMPLOYEE_QUERY);
        JOptionPane.showMessageDialog(new Frame(), "Dodano nowego pracownika.");
    }

    public void initialize(URL location, ResourceBundle resources) {

        try {
            connection = (DriverManager.getConnection("jdbc:mysql://localhost/firma?useLegacyDatetimeCode=false&serverTimezone=UTC&" +
                    "user=root&password=database123"));
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
