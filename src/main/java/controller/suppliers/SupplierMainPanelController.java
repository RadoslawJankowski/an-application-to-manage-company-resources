package controller.suppliers;

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
import model.Supplier;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static db.DBConnector.getConnection;
import static mysqlCommands.employeeCommands.SelectEmployeesQuerys.SELECT_ALL_FROM_SUPPLIERS;

public class SupplierMainPanelController implements GeneralMethodsOfClasses {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;

    private static SupplierMainPanelController instance;

    public static synchronized SupplierMainPanelController getInstanceOfSuppMainPanel() {
        if (instance == null) {
            instance = new SupplierMainPanelController();
        }
        return instance;
    }

    @FXML
    TableView<Supplier> tableViewSuppliers;

    @FXML
    TableColumn<Supplier, Integer> supplier_id;
    @FXML
    TableColumn<Supplier, String> name;
    @FXML
    TableColumn<Supplier, Integer> type_of_products;
    @FXML
    TableColumn<Supplier, String > representative;
    @FXML
    TableColumn<Supplier, String > contact;
    @FXML
    TableColumn<Supplier, String > city;
    @FXML
    TableColumn<Supplier, String > address;
    @FXML
    TableColumn<Supplier, String > email;

    Supplier supplierToDelete;

    ObservableList<Supplier> observableList = FXCollections.observableArrayList();

    @Override
    public void backToPreviousWindow(ActionEvent event) throws IOException {
        AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/MainMenuPanel.fxml"));
        Scene gameSceneView = new Scene(gameViewParent);
        Stage gameWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        gameWindow.setScene(gameSceneView);
        gameWindow.show();
    }

    /**
     * <p>The method at the beginning removes all results from {@code tableViewSupplier} and sets opacity to 1.</p>
     * <p>Establishes a connection via {@link Connection} {@code connection = getConnection()}.</p>
     * <p>Then execute the query {@code SELECT_ALL_FROM_SUPPLIERS}.</p>
     * Using {@code while(resultSet.next())} pulls out all suppliers and adds them to the {@link ObservableList} {@code observableList}.
     * <p>At the end closes all connections.</p>
     *
     * @throws SQLException
     */
    @Override
    public void selectAll() throws SQLException {

        tableViewSuppliers.getItems().clear();
        tableViewSuppliers.setOpacity(1);

        try {
            connection = getConnection();
            resultSet = connection.createStatement().executeQuery(SELECT_ALL_FROM_SUPPLIERS);
            while (resultSet.next()) {
                observableList.add(new Supplier(resultSet.getInt("supplier_id"),
                        resultSet.getString("name"), resultSet.getInt("type_of_products"),
                        resultSet.getString("representative"), resultSet.getString("contact"),
                        resultSet.getString("city"), resultSet.getString("address"),
                        resultSet.getString("email")));
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        supplier_id.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type_of_products.setCellValueFactory(new PropertyValueFactory<>("type_of_products"));
        representative.setCellValueFactory(new PropertyValueFactory<>("representative"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableViewSuppliers.setItems(observableList);

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

    @Override
    public void add() {

        try {
            AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/supplierFXML/AddSupplierWindow.fxml"));
            Scene gameSceneView = new Scene(gameViewParent);
            Stage newWindow = new Stage();
            newWindow.setScene(gameSceneView);
            newWindow.setTitle("Dodaj dostawcę");
            newWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSelected() throws IOException {

        if (tableViewSuppliers.getSelectionModel().getSelectedItem() != null) {
            getInstanceOfSuppMainPanel().setSupplierToDelete(tableViewSuppliers.getSelectionModel().getSelectedItem());
            AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/supplierFXML/AcceptDeleteSupplier.fxml"));
            Scene gameSceneView = new Scene(gameViewParent);
            Stage newWindow = new Stage();
            newWindow.setScene(gameSceneView);
            newWindow.setTitle("Usuwanie dostawcy");
            newWindow.show();
        } else JOptionPane.showMessageDialog(new Frame(), "Nie wybrano dostawcy!");
    }

    public void setSupplierToDelete(Supplier supplierToDelete) {
        this.supplierToDelete = supplierToDelete;
    }
}
