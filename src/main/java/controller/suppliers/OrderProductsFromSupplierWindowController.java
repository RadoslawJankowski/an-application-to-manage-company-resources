package controller.suppliers;

import db.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static controller.suppliers.SupplierMainPanelController.getInstanceOfSuppMainPanel;
import static db.DBConnector.getConnection;
import static mysqlCommands.insertIntoQueries.unfinished_order.InsertIntoUnfinishedOrderQueries.INSERT_PRODUCTS_INTO_UNFINISHED_ORDER;
import static mysqlCommands.selectQueries.suppliers_products.SelectFromSupplierProductsQueries.SELECT_ALL_FROM_SUPPLIERS_PRODUCTS;
import static mysqlCommands.selectQueries.unfinished_order.SelectFromUnfinishedOrderQueries.SELECT_PRICE_AND_AMOUNT_FROM_UNFINISHED_ORDER;

/**
 * Controller for {@code OrderProductsFromSupplierWindow.fxml }.
 * <p>Methods:</p>
 * <li> {@link OrderProductsFromSupplierWindowController#addToOrderButtonPushed()}</li>
 * <li> {@link OrderProductsFromSupplierWindowController#totalOrderSumValue(Text)}</li>
 * <li> {@link OrderProductsFromSupplierWindowController#initialize(URL, ResourceBundle)}</li>
 */
public class OrderProductsFromSupplierWindowController implements Initializable {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;

    private double totalOrderValues;

    @FXML
    TableView<Product> supplierProductsTableView;

    @FXML
    TableColumn<Product, Integer> supplierProductId;

    @FXML
    TableColumn<Product, String> supplierProductName;

    @FXML
    TableColumn<Product, Integer> supplierProductType;

    @FXML
    TableColumn<Product, Double> supplierProductPrice;

    @FXML
    TextField productAmountTextField;

    @FXML
    Button addToOrderButton, orderButton, currentOrderView;

    @FXML
    Text orderTotalText, orderTotalSum;

    ObservableList<Product> observableList = FXCollections.observableArrayList();
    ObservableList<Double> totalOrderValueList = FXCollections.observableArrayList();

    /**
     * Connects to the database using {@link DBConnector#getConnection()}.
     * The method adds the product selected by the user to the {@code unfinished_order} database
     * after giving the quantity and pressing the "{@code add to order}" {@link Button} button,
     * and then deletes from the {@link TextField} field the user-specified amount preparing for the next order.
     * If the user does not choose products, he will be informed. You can not add a zero or a negative value.
     * @throws SQLException
     */
    public void addToOrderButtonPushed() throws SQLException {

        if (supplierProductsTableView.getSelectionModel().getSelectedItem() != null && Integer.parseInt(productAmountTextField.getText()) > 0) {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_PRODUCTS_INTO_UNFINISHED_ORDER);
            ((PreparedStatement) statement).setInt(1, supplierProductsTableView.getSelectionModel().getSelectedItem().getProduct_id());
            ((PreparedStatement) statement).setString(2, supplierProductsTableView.getSelectionModel().getSelectedItem().getName());
            ((PreparedStatement) statement).setInt(3, supplierProductsTableView.getSelectionModel().getSelectedItem().getProduct_type());
            ((PreparedStatement) statement).setDouble(4, supplierProductsTableView.getSelectionModel().getSelectedItem().getPrice());
            ((PreparedStatement) statement).setInt(5, Integer.parseInt(productAmountTextField.getText()));
            ((PreparedStatement) statement).setString(6, getInstanceOfSuppMainPanel().getSelectedSupplier().getName());
            ((PreparedStatement) statement).setDouble(7, supplierProductsTableView.getSelectionModel().getSelectedItem().getPrice() * Integer.parseInt(productAmountTextField.getText()));
            ((PreparedStatement) statement).executeUpdate();

            productAmountTextField.setText(null);

            totalOrderSumValue(orderTotalSum);

            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } else JOptionPane.showMessageDialog(new Frame(), "Nie wybrano produktu lub podano niewłaściwą wartość!");
    }

    /**
     * Method load {@code ShowUnfinishedOrderWindow.fxml ).
     */
    public void showUnfinishedOrderButtonPushed(){
        try {
            AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/supplierFXML/ShowUnfinishedOrderWindow.fxml"));
            Scene gameSceneView = new Scene(gameViewParent);
            Stage newWindow = new Stage();
            newWindow.setScene(gameSceneView);
            newWindow.setTitle("Bieżące zamówienie");
            newWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method cleans the {@link ObservableList} {@code totalOrderValueList}.
     * Connects to the database through the {@link db.DBConnector#getConnection} class and uses
     * query {@link mysqlCommands.selectQueries.unfinished_order.SelectFromUnfinishedOrderQueries#SELECT_PRICE_AND_AMOUNT_FROM_UNFINISHED_ORDER}.
     * Read all data corresponding to the columns "{@code amount}" and "{@code price}" and add
     * them to the {@link ObservableList} {@code totalOrderValueList}.
     * Using {@link java.util.stream.Stream} to maps values ​​to double and sums.
     * Finally, he sets {@link FXML} {@link Text} on the value of the order.
     * @param field
     * @throws SQLException
     */
    public void totalOrderSumValue(Text field) throws SQLException {

        totalOrderValueList.clear();

        connection = getConnection();
        resultSet = connection.createStatement().executeQuery(SELECT_PRICE_AND_AMOUNT_FROM_UNFINISHED_ORDER);
        while (resultSet.next()){
            totalOrderValueList.add(resultSet.getInt("amount") * resultSet.getDouble("price"));
        }

        setTotalOrderValues(totalOrderValueList.stream().mapToDouble(Double::doubleValue).sum());
        field.setText(String.valueOf(getTotalOrderValues()));

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

    public double getTotalOrderValues() {
        return totalOrderValues;
    }

    public void setTotalOrderValues(double totalOrderValues) {
        this.totalOrderValues = totalOrderValues;
    }

    /**
     * In initialization, after pressing the "order" button in the {@link SupplierMainPanelController} class,
     * an {@link ObservableList} observableList is created based on the supplier chosen by the user and loaded with products
     * of the same type as the supplier's products. At the same time, the {@link OrderProductsFromSupplierWindowController#totalOrderSumValue} method
     * creates a connection to the unfinished_order database, supports the quantity of goods and multiplies
     * them by the price, and then displays the total amount of the order.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            totalOrderSumValue(orderTotalSum);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        supplierProductsTableView.getItems().clear();
        supplierProductsTableView.setOpacity(1);

        try {
            connection = getConnection();
            resultSet = connection.createStatement().executeQuery(SELECT_ALL_FROM_SUPPLIERS_PRODUCTS);

            while (resultSet.next()){
                if (resultSet.getInt("product_type") == getInstanceOfSuppMainPanel().getSelectedSupplier().getType_of_products()){
                    observableList.add(new Product(
                            resultSet.getInt("product_id"),
                            resultSet.getString("name"),
                            resultSet.getInt("product_type"),
                            resultSet.getDouble("price")));
                }
            }
        }catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        supplierProductId.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        supplierProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        supplierProductType.setCellValueFactory(new PropertyValueFactory<>("product_type"));
        supplierProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        supplierProductsTableView.setItems(observableList);

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
