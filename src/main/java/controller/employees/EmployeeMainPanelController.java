package controller.employees;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static mysqlCommands.SelectEmployeesQuerys.*;
import static db.DBConnector.getConnection;
import static mysqlCommands.DeleteEmployeeQuery.*;

public class EmployeeMainPanelController implements Initializable {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;

    private static EmployeeMainPanelController instance;

    public static synchronized EmployeeMainPanelController getInstanceOfEmpMainPanel() {
        if (instance == null) {
            instance = new EmployeeMainPanelController();
        }
        return instance;
    }

    @FXML
    TableView<Employee> tableViewEmployees;

    @FXML
    TableColumn<Employee, Integer> emp_id;
    @FXML
    TableColumn<Employee, String> firstName;
    @FXML
    TableColumn<Employee, String> lastName;
    @FXML
    TableColumn<Employee, Date> birthDayDate;
    @FXML
    TableColumn<Employee, String> sex;
    @FXML
    TableColumn<Employee, Integer> position;
    @FXML
    TableColumn<Employee, BigDecimal> salary;
    @FXML
    TableColumn<Employee, Timestamp> work_from;

    ObservableList<Employee> observableList = FXCollections.observableArrayList();

    Employee employeeToUpdate;

    public void selectAllEmployees() {

        tableViewEmployees.getItems().clear();
        tableViewEmployees.setOpacity(1);
        try {
            connection = getConnection();
            resultSet = connection.createStatement().executeQuery(SELECT_ALL_FROM_EMPLOYEES);

            while (resultSet.next()) {
                observableList.add(new Employee(resultSet.getInt("emp_id"),
                        resultSet.getString("firstName"), resultSet.getString("lastName"),
                        resultSet.getDate("birthDayDate"), resultSet.getString("sex").toUpperCase(),
                        resultSet.getInt("empPosition"), resultSet.getBigDecimal("salary"),
                        resultSet.getTimestamp("work_from")));
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        emp_id.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        birthDayDate.setCellValueFactory(new PropertyValueFactory<>("birthDayDate"));
        sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        position.setCellValueFactory(new PropertyValueFactory<>("empPosition"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        work_from.setCellValueFactory(new PropertyValueFactory<>("work_from"));
        tableViewEmployees.setItems(observableList);
    }

    public void addEmployeeWindowLoader() {

        try {
            AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/employeeFXML/AddEmployeeWindow.fxml"));
            Scene gameSceneView = new Scene(gameViewParent);
            Stage newWindow = new Stage();
            newWindow.setScene(gameSceneView);
            newWindow.setTitle("Dodaj nowego pracownika");
            newWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method by which we can delete the employee chosen by us in the main panel.
     * By deleting, we are asking if we really want to do it.
     * After selecting "yes", the employee is removed from the database and the view of employees is refreshed.
     *
     * @throws SQLException
     */
    public void deleteSelectedEmployee() throws SQLException {

        int p = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć tego pracownika?", "Usuwanie pracownika", JOptionPane.YES_NO_OPTION);

        if (p == 0) {
            try {
                Employee employeeToDelete = tableViewEmployees.getSelectionModel().getSelectedItem();
                statement = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID);
                ((PreparedStatement) statement).setInt(1, employeeToDelete.getEmp_id());
                ((PreparedStatement) statement).executeUpdate();

                selectAllEmployees();
            } catch (SQLException e) {
                System.out.println(e);
            }

            statement.close();
            connection.close();
        }
    }

    public void updateEmployeeDataWindowLoader() throws IOException {

            getInstanceOfEmpMainPanel().setEmployeeToUpdate(tableViewEmployees.getSelectionModel().getSelectedItem());

            AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/employeeFXML/UpdateEmployeeWindow.fxml"));
            Scene gameSceneView = new Scene(gameViewParent);
            Stage newWindow = new Stage();
            newWindow.setScene(gameSceneView);
            newWindow.setTitle("Zmień dane pracownika");
            newWindow.show();
        }

    public void backToPreviousWindow(ActionEvent event) throws IOException {

        AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/MainMenuPanel.fxml"));
        Scene gameSceneView = new Scene(gameViewParent);
        Stage gameWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        gameWindow.setScene(gameSceneView);
        gameWindow.show();
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    public Employee getEmployeeToUpdate() {
        return employeeToUpdate;
    }

    public void setEmployeeToUpdate(Employee employeeToUpdate) {
        this.employeeToUpdate = employeeToUpdate;
    }
}
