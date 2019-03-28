package controller.employees;

import controller.interfaces.GeneralMethodsOfClasses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;

import static mysqlCommands.employeeCommands.SelectEmployeesQuerys.*;
import static db.DBConnector.getConnection;

public class EmployeeMainPanelController implements GeneralMethodsOfClasses {

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
    Employee employeeToDelete;

    /**
     *  <p>Method load the main window based on {@link fxmlFiles} / MainMenuPanel.fxml </p>
     *
     *  @param event
     *  @throws IOException
     */
    @Override
    public void backToPreviousWindow(ActionEvent event) throws IOException {
        AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/MainMenuPanel.fxml"));
        Scene gameSceneView = new Scene(gameViewParent);
        Stage gameWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        gameWindow.setScene(gameSceneView);
        gameWindow.show();
    }

    /**
     * <p>The method at the beginning removes all results from {@code tableViewEmployees} and sets opacity to 1.</p>
     * <p>Establishes a connection via {@link Connection} {@code connection = getConnection()}.</p>
     * <p>Then execute the query {@code SELECT_ALL_FROM_EMPLOYEES}.</p>
     * Using {@code while(resultSet.next())} pulls out all employees and adds them to the {@link ObservableList} {@code observableList}.
     * <p>At the end closes all connections.</p>
     *
     * @throws SQLException
     */
    @Override
    public void selectAll() throws SQLException {

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

        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }

    /**
     * <p>Open a new window from the {@link fxmlFiles.employeeFXML} / AddEmployeeWindow.fxml files.</p>
     */
    @Override
    public void add() {

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
     * <p>Method set {@link TableView} field {@code tableViewEmployee} on the value of the
     * user-selected fields in the tableView from {@link controller.employees.EmployeeMainPanelController}</p>
     *
     * <p>And then open a window from {@link fxmlFiles.employeeFXML} / AcceptDeleteEmployee.fxml </p>
     * <p> If the user does not choose a government employee, he will be informed "No employee selected!"</p>
     *
     * @throws SQLException
     */
    @Override
    public void deleteSelected() throws IOException {

        if (tableViewEmployees.getSelectionModel().getSelectedItem() != null) {
            getInstanceOfEmpMainPanel().setEmployeeToDelete(tableViewEmployees.getSelectionModel().getSelectedItem());
            AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/employeeFXML/AcceptDeleteEmployee.fxml"));
            Scene gameSceneView = new Scene(gameViewParent);
            Stage newWindow = new Stage();
            newWindow.setScene(gameSceneView);
            newWindow.setTitle("Dodaj nowego pracownika");
            newWindow.show();
        } else JOptionPane.showMessageDialog(new Frame(), "Nie wybrano pracownika!");
    }

    /**
     * <p>Method set {@link TableView} field {@code tableViewEmployee} on the value of the
     * user-selected fields in the tableView from {@link controller.employees.EmployeeMainPanelController}</p>
     *
     * <p>And then open a window from {@link fxmlFiles.employeeFXML} / UpdateEmployeeWindow.fxml </p>
     * <p> If the user does not choose a government employee, he will be informed "No employee selected!"</p>
     *
     * @throws SQLException
     */
    public void updateEmployeeDataWindowLoader() throws IOException {

        if (tableViewEmployees.getSelectionModel().getSelectedItem() != null) {
            getInstanceOfEmpMainPanel().setEmployeeToUpdate(tableViewEmployees.getSelectionModel().getSelectedItem());

            AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/employeeFXML/UpdateEmployeeWindow.fxml"));
            Scene gameSceneView = new Scene(gameViewParent);
            Stage newWindow = new Stage();
            newWindow.setScene(gameSceneView);
            newWindow.setTitle("Zmień dane pracownika");
            newWindow.show();
        }
        else JOptionPane.showMessageDialog(new Frame(), "Nie wybrano pracownika!");
    }

    public Employee getEmployeeToUpdate() {
        return employeeToUpdate;
    }

    public void setEmployeeToUpdate(Employee employeeToUpdate) {
        this.employeeToUpdate = employeeToUpdate;
    }

    public Employee getEmployeeToDelete() {
        return employeeToDelete;
    }

    public void setEmployeeToDelete(Employee employeeToDelete) {
        this.employeeToDelete = employeeToDelete;
    }

}
