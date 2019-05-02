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
import model.FinishedOrder;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.List;

import static controller.suppliers.SupplierMainPanelController.getInstanceOfSuppMainPanel;
import static db.DBConnector.getConnectionToCompany;
import static db.DBConnector.getConnectionToOrders;
import static model.FinishedOrder.getInstanceOfFinishedOrder;
import static mysqlCommands.deleteQueries.unfinished_order.DeleteFromUnfinishedOrderQueries.DELETE_ALL_FROM_UNFINISHED_ORDER;
import static mysqlCommands.insertIntoQueries.companyDatabase.unfinished_order.InsertIntoUnfinishedOrderQueries.INSERT_PRODUCTS_INTO_UNFINISHED_ORDER;
import static mysqlCommands.insertIntoQueries.ordersDatabase.order_to_the_supplier.InsertIntoOrderToTheSupplierQueries.INSERT_INTO_ORDER_TO_THE_SUPPLIER;
import static mysqlCommands.selectQueries.companyDatabase.suppliers_products.SelectFromSupplierProductsQueries.SELECT_ALL_FROM_SUPPLIERS_PRODUCTS;
import static mysqlCommands.selectQueries.companyDatabase.unfinished_order.SelectFromUnfinishedOrderQueries.SELECT_ALL_FROM_UNFINISHED_ORDER;
import static mysqlCommands.selectQueries.companyDatabase.unfinished_order.SelectFromUnfinishedOrderQueries.SELECT_PRICE_AND_AMOUNT_FROM_UNFINISHED_ORDER;
import static mysqlCommands.selectQueries.ordersDatabase.order_invoiceNumber.SelectFromOrderInvoiceNumberQueries.SELECT_FROM_ORDER_INVOICE_NUMBER;
import static mysqlTablesSchema.ordersDatabaseTables.order_to_the_supplier.OrderToTheSupplier.ORDER_TO_THE_SUPPLIER_TABLE;

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
    Connection connection1 = null;
    Connection connection2 = null;

    ResultSet resultSet = null;
    ResultSet resultSet1 = null;

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

    List<Integer> supplierIdList = Collections.synchronizedList(new ArrayList<>());

    ObservableList<Product> observableList = FXCollections.observableArrayList();
    ObservableList<Double> totalOrderValueList = FXCollections.observableArrayList();

    /**
     * Connects to the database using {@link DBConnector#getConnectionToCompany()}.
     * The method adds the product selected by the user to the {@code unfinished_order} database
     * after giving the quantity and pressing the "{@code add to order}" {@link Button} button,
     * and then deletes from the {@link TextField} field the user-specified amount preparing for the next order.
     * If the user does not choose products, he will be informed. You can not add a zero or a negative value.
     *
     * @throws SQLException
     */
    public void addToOrderButtonPushed() throws SQLException {

        if (productAmountTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(new Frame(), "Należy podać ilość sztuk!");
        } else if (supplierProductsTableView.getSelectionModel().getSelectedItem() != null && Integer.parseInt(productAmountTextField.getText()) > 0) {
            connection = getConnectionToCompany();
            statement = connection.prepareStatement(INSERT_PRODUCTS_INTO_UNFINISHED_ORDER);
            ((PreparedStatement) statement).setInt(1, supplierProductsTableView.getSelectionModel().getSelectedItem().getProduct_id());
            ((PreparedStatement) statement).setString(2, supplierProductsTableView.getSelectionModel().getSelectedItem().getName());
            ((PreparedStatement) statement).setInt(3, supplierProductsTableView.getSelectionModel().getSelectedItem().getProduct_type());
            ((PreparedStatement) statement).setDouble(4, supplierProductsTableView.getSelectionModel().getSelectedItem().getPrice());
            ((PreparedStatement) statement).setInt(5, Integer.parseInt(productAmountTextField.getText()));
            ((PreparedStatement) statement).setString(6, getInstanceOfSuppMainPanel().getSelectedSupplier().getName());
            ((PreparedStatement) statement).setInt(7, getInstanceOfSuppMainPanel().getSelectedSupplier().getSupplier_id());
            ((PreparedStatement) statement).setDouble(8, supplierProductsTableView.getSelectionModel().getSelectedItem().getPrice() * Integer.parseInt(productAmountTextField.getText()));
            ((PreparedStatement) statement).executeUpdate();

            supplierIdList.add(getInstanceOfSuppMainPanel().getSelectedSupplier().getSupplier_id());
            System.out.println(supplierIdList);
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
    public void showUnfinishedOrderButtonPushed() {
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

    public void placeOrderButtonPushed() throws SQLException {

        connection = getConnectionToCompany();
        resultSet = connection.createStatement().executeQuery(SELECT_ALL_FROM_UNFINISHED_ORDER);
        getInstanceOfFinishedOrder().generateInvoiceNumber();

        connection1 = getConnectionToOrders();
        statement = connection1.createStatement();
        statement.executeUpdate(ORDER_TO_THE_SUPPLIER_TABLE);
        connection1.close();
        statement.close();

        connection1 = getConnectionToOrders();
        statement = connection1.prepareStatement(INSERT_INTO_ORDER_TO_THE_SUPPLIER);

        connection2 = getConnectionToOrders();
        resultSet1 = connection2.createStatement().executeQuery(SELECT_FROM_ORDER_INVOICE_NUMBER);

        // TODO zrobic jakos w tym while patent na sortowanie wynikow z resultSet i tworzenie tylu tablic ilu jest
        // TODO dostawccow i dodawanie odpowiednio produktow z unfinished_order do tych tablic
        while (resultSet.next()) {
                ((PreparedStatement) statement).setInt(1, getInstanceOfFinishedOrder().getInvoiceNumber());
                ((PreparedStatement) statement).setString(2, resultSet.getString("supplier"));
                ((PreparedStatement) statement).setInt(3, resultSet.getInt("supplier_id"));
                ((PreparedStatement) statement).setInt(4, resultSet.getInt("product_id"));
                ((PreparedStatement) statement).setString(5, resultSet.getString("name"));
                ((PreparedStatement) statement).setDouble(6, resultSet.getDouble("price"));
                ((PreparedStatement) statement).setInt(7, resultSet.getInt("amount"));
                ((PreparedStatement) statement).setDouble(8, resultSet.getDouble("price") * resultSet.getInt("amount"));
                ((PreparedStatement) statement).executeUpdate();
        }
        statement = connection.prepareStatement(DELETE_ALL_FROM_UNFINISHED_ORDER);
        ((PreparedStatement) statement).executeUpdate();
    }

    /**
     * The method cleans the {@link ObservableList} {@code totalOrderValueList}.
     * Connects to the database through the {@link db.DBConnector#getConnectionToCompany} class and uses
     * query {@link mysqlCommands.selectQueries.companyDatabase.unfinished_order.SelectFromUnfinishedOrderQueries#SELECT_PRICE_AND_AMOUNT_FROM_UNFINISHED_ORDER}.
     * Read all data corresponding to the columns "{@code amount}" and "{@code price}" and add
     * them to the {@link ObservableList} {@code totalOrderValueList}.
     * Using {@link java.util.stream.Stream} to maps values ​​to double and sums.
     * Finally, he sets {@link FXML} {@link Text} on the value of the order.
     *
     * @param field
     * @throws SQLException
     */
    public void totalOrderSumValue(Text field) throws SQLException {

        totalOrderValueList.clear();

        connection = getConnectionToCompany();
        resultSet = connection.createStatement().executeQuery(SELECT_PRICE_AND_AMOUNT_FROM_UNFINISHED_ORDER);
        while (resultSet.next()) {
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
     *
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
            connection = getConnectionToCompany();
            resultSet = connection.createStatement().executeQuery(SELECT_ALL_FROM_SUPPLIERS_PRODUCTS);

            while (resultSet.next()) {
                if (resultSet.getInt("product_type") == getInstanceOfSuppMainPanel().getSelectedSupplier().getType_of_products()) {
                    observableList.add(new Product(
                            resultSet.getInt("product_id"),
                            resultSet.getString("name"),
                            resultSet.getInt("product_type"),
                            resultSet.getDouble("price")));
                }
            }
        } catch (SQLException ex) {
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
