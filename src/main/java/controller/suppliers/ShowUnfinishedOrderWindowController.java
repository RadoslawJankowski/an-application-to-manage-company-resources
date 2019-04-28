package controller.suppliers;

import db.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.UnfinishedOrder;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static db.DBConnector.getConnection;
import static mysqlCommands.deleteQueries.unfinished_order.DeleteFromUnfinishedOrderQueries.DELETE_FROM_UNFINISHED_ORDER_BY_ID;
import static mysqlCommands.selectQueries.unfinished_order.SelectFromUnfinishedOrderQueries.SELECT_ID_NAME_PRICE_AMOUNT_TYPE_SUPPLIER_SUM_FROM_UNFINISHED_ORDER;

/**
 * Controller for {@code ShowUnfinishedOrderWindow.fxml }.
 * <p>Methods:</p>
 * <li>{@link ShowUnfinishedOrderWindowController#deleteProductFromUnfinishedOrder() }</li>
 * <li>{@link ShowUnfinishedOrderWindowController#cancelButtonPushed() }</li>
 * <li>{@link ShowUnfinishedOrderWindowController#initialize(URL location, ResourceBundle resources) }</li>
 */
public class ShowUnfinishedOrderWindowController implements Initializable {

    Statement statement = null;
    Connection connection = null;
    ResultSet resultSet = null;

    @FXML
    TableView<UnfinishedOrder> unfinishedOrderTableView;

    @FXML
    TableColumn<UnfinishedOrder, Integer> productIdColumn;

    @FXML
    TableColumn<UnfinishedOrder, String> productNameColumn;

    @FXML
    TableColumn<UnfinishedOrder, Integer> productTypeColumn;

    @FXML
    TableColumn<UnfinishedOrder, Double> productPriceColumn;

    @FXML
    TableColumn<UnfinishedOrder, Double> productAmountColumn;

    @FXML
    TableColumn<UnfinishedOrder, String> productSupplierColumn;

    @FXML
    TableColumn<UnfinishedOrder, Double> productSumColumn;

    @FXML
    Text orderSumText;

    @FXML
    Button deleteProductButton, cancelButton;

    ObservableList<UnfinishedOrder> observableList = FXCollections.observableArrayList();

    /**
     * If the user selects the product, the connection to the {@code unfinished_order} database
     * is established using {@link DBConnector#getConnection()}. A statement is prepared using
     * {@code DELETE_FROM_UNFINISHED_ORDER_BY_ID}. Id is set by the selected model and then the product
     * is removed from the database. The user is informed about the correct removal of the product
     * and the window is closed. If the user does not choose the product he will get a message that
     * he has not chosen the product.
     * @throws SQLException
     */
    public void deleteProductFromUnfinishedOrder() throws SQLException {

        if (unfinishedOrderTableView.getSelectionModel().getSelectedItem() != null) {
            connection = getConnection();
            statement = connection.prepareStatement(DELETE_FROM_UNFINISHED_ORDER_BY_ID);
            ((PreparedStatement) statement).setInt(1, unfinishedOrderTableView.getSelectionModel().getSelectedItem().getProduct_id());
            ((PreparedStatement) statement).executeUpdate();

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
            JOptionPane.showMessageDialog(new Frame(), "Usunięto produkt " +
                    unfinishedOrderTableView.getSelectionModel().getSelectedItem().getName() + " o numerze id: " +
                    unfinishedOrderTableView.getSelectionModel().getSelectedItem().getProduct_id());
            Stage stage = (Stage) deleteProductButton.getScene().getWindow();
            stage.close();
        } else JOptionPane.showMessageDialog(new Frame(), "Nie wybrano produktu!");
    }

    /**
     * {@link ShowUnfinishedOrderWindowController#cancelButton} closes the window with unfinished order.
     */
    public void cancelButtonPushed(){
        Stage stage = (Stage) deleteProductButton.getScene().getWindow();
        stage.close();
    }

    /**
     * In the initialization, the text orderSumText is set using the
     * {@link OrderProductsFromSupplierWindowController#totalOrderSumValue(Text)} method.
     * Connects to the {@code unfinished_order} database using {@link DBConnector#getConnection()}  and
     * extracts results using {@link ResultSet} {@code resultSet} and adds them to {@link TableView} {@code unfinishedOrderTableView}.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // setting the orderSumText field
        OrderProductsFromSupplierWindowController controller = new OrderProductsFromSupplierWindowController();
        try {
            controller.totalOrderSumValue(orderSumText);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        unfinishedOrderTableView.getItems().clear();

        try {
            connection = getConnection();
            resultSet = connection.createStatement().executeQuery(SELECT_ID_NAME_PRICE_AMOUNT_TYPE_SUPPLIER_SUM_FROM_UNFINISHED_ORDER);

            while (resultSet.next()) {
                {
                    observableList.add(new UnfinishedOrder(
                            resultSet.getInt("product_id"),
                            resultSet.getString("name"),
                            resultSet.getInt("type_of_product"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("amount"),
                            resultSet.getString("supplier"),
                            resultSet.getDouble("price") * resultSet.getInt("amount")));
                }
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type_of_product"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        productSupplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        productSumColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));

        unfinishedOrderTableView.setItems(observableList);

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
