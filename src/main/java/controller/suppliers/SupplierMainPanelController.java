package controller.suppliers;

import controller.interfaces.GeneralMethodsForMainWindowClassControllers;
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

import static db.DBConnector.getConnectionToCompany;
import static mysqlCommands.selectQueries.companyDatabase.suppliers.SelectFromSuppliersQueries.SELECT_ALL_FROM_SUPPLIERS;

/**
 * Main Controller for supplier view
 * <li>{@code AddSupplierWindow.fxml }</li>
 * <li>{@code DeleteSupplier.fxml }</li>
 * <li>{@code OrderProductsFromSupplierWindow.fxml }</li>
 * <li>{@code ShowUnfinishedOrderWindow.fxml }</li>
 * <li>{@code SupplierMainPanelController.fxml }</li>
 * <li>{@code UpdateSupplierWindow.fxml }</li>
 * <p></p>
 * <p>Methods:</p>
 * <li>{@link SupplierMainPanelController#backToPreviousWindow(ActionEvent event)}</li>
 * <li>{@link SupplierMainPanelController#selectAll()}</li>
 * <li>{@link SupplierMainPanelController#add()}</li>
 * <li>{@link SupplierMainPanelController#deleteSelected()}</li>
 * <li>{@link SupplierMainPanelController#updateSupplierDataWindowLoader()}</li>
 * <li>{@link SupplierMainPanelController#orderProductsWindowLoader()}</li>
 */
public class SupplierMainPanelController implements GeneralMethodsForMainWindowClassControllers {

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
    TableColumn<Supplier, String> representative;
    @FXML
    TableColumn<Supplier, String> contact;
    @FXML
    TableColumn<Supplier, String> city;
    @FXML
    TableColumn<Supplier, String> address;
    @FXML
    TableColumn<Supplier, String> email;

    Supplier supplierToDelete;
    Supplier supplierToUpdate;
    Supplier selectedSupplier;

    ObservableList<Supplier> observableList = FXCollections.observableArrayList();

    /**
     * <p>Method load the main window based on {@link fxmlFiles} / MainMenuPanel.fxml </p>
     *
     * @param event
     * @throws IOException
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
     * <p>The method at the beginning removes all results from {@code tableViewSupplier} and sets opacity to 1.</p>
     * <p>Establishes a connection via {@link Connection} {@code connection = getConnectionToCompany()}.</p>
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
            connection = getConnectionToCompany();
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
            AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/supplierFXML/DeleteSupplier.fxml"));
            Scene gameSceneView = new Scene(gameViewParent);
            Stage newWindow = new Stage();
            newWindow.setScene(gameSceneView);
            newWindow.setTitle("Usuwanie dostawcy");
            newWindow.show();
        } else JOptionPane.showMessageDialog(new Frame(), "Nie wybrano dostawcy!");
    }

    /**
     * <p>Method set {@link TableView} field {@code tableViewSupplier} on the value of the
     * user-selected fields in the tableView from {@link controller.suppliers.SupplierMainPanelController}</p>
     *
     * <p>And then open a window from {@link fxmlFiles.supplierFXML} / UpdateSupplierWindow.fxml </p>
     * <p> If the user does not choose a government employee, he will be informed "No supplier selected!"</p>
     *
     * @throws SQLException
     */
    public void updateSupplierDataWindowLoader() throws IOException {

        if (tableViewSuppliers.getSelectionModel().getSelectedItem() != null) {
            getInstanceOfSuppMainPanel().setSupplierToUpdate(tableViewSuppliers.getSelectionModel().getSelectedItem());

            AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/supplierFXML/UpdateSupplierWindow.fxml"));
            Scene gameSceneView = new Scene(gameViewParent);
            Stage newWindow = new Stage();
            newWindow.setScene(gameSceneView);
            newWindow.setTitle("Zmień dane dostawcy");
            newWindow.show();
        } else JOptionPane.showMessageDialog(new Frame(), "Nie wybrano dostawcy!");
    }

    public void orderProductsWindowLoader() {

        if (tableViewSuppliers.getSelectionModel().getSelectedItem() != null) {
            getInstanceOfSuppMainPanel().setSelectedSupplier(tableViewSuppliers.getSelectionModel().getSelectedItem());
            try {
                AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/supplierFXML/OrderProductsFromSupplierWindow.fxml"));
                Scene gameSceneView = new Scene(gameViewParent);
                Stage newWindow = new Stage();
                newWindow.setScene(gameSceneView);
                newWindow.setTitle("Zamów towar");
                newWindow.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else JOptionPane.showMessageDialog(new Frame(), "Nie wybrano dostawcy!");
    }

    public void setSupplierToDelete(Supplier supplierToDelete) {
        this.supplierToDelete = supplierToDelete;
    }

    public Supplier getSupplierToUpdate() {
        return supplierToUpdate;
    }

    public void setSupplierToUpdate(Supplier supplierToUpdate) {
        this.supplierToUpdate = supplierToUpdate;
    }

    public Supplier getSelectedSupplier() {
        return selectedSupplier;
    }

    public void setSelectedSupplier(Supplier selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
    }
}
