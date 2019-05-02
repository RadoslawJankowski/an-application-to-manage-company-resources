package model;

import db.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static mysqlCommands.insertIntoQueries.ordersDatabase.invoices_numbers.InsertIntoInvoicesNumbers.INSERT_INTO_INVOICES_NUMBERS;
import static mysqlCommands.selectQueries.ordersDatabase.invoices_numbers.SelectFromInvoicesNumbersQueries.SELECT_ALL_FROM_INVOICES_NUMBERS;

public class FinishedOrder {

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    private int product_id;
    private String name;
    private int type_of_product;
    private double price;
    private int amount;
    private String supplier;
    private int supplier_id;
    private double sum;
    private int invoiceNumber;

    List<Integer> invoiceNumberList = new ArrayList<>();

    private volatile static FinishedOrder instance;

    private FinishedOrder() {
    }

    public static FinishedOrder getInstanceOfFinishedOrder() {
        if (instance == null) {
            synchronized (FinishedOrder.class) {
                if (instance == null) {
                    instance = new FinishedOrder();
                }
            }
        }

        return instance;
    }

    public FinishedOrder(int product_id, String name, int type_of_product, double price, int amount, String supplier, int supplier_id, double sum) {
        this.product_id = product_id;
        this.name = name;
        this.type_of_product = type_of_product;
        this.price = price;
        this.amount = amount;
        this.supplier = supplier;
        this.supplier_id = supplier_id;
        this.sum = sum;
    }

    public Integer generateNumber(){
        Random random = new Random();
        setInvoiceNumber(99999 + random.nextInt(9000000));
        return invoiceNumber;
    }

    public boolean isInvoiceNumberInTheTable() throws SQLException {

        int numbersFromTable;

        connection = DBConnector.getConnectionToOrders();
        resultSet = connection.createStatement().executeQuery(SELECT_ALL_FROM_INVOICES_NUMBERS);
        generateNumber();
        while (resultSet.next()){
            numbersFromTable = resultSet.getInt("invoice_number");
            invoiceNumberList.add(numbersFromTable);
        }
        if (invoiceNumberList.contains(invoiceNumber)){
            return true;
        }
        else return false;
    }

    public void generateInvoiceNumber() throws SQLException {
        generateNumber();
        if (isInvoiceNumberInTheTable()){
            generateNumber();
        }
        else {
            connection = DBConnector.getConnectionToOrders();
            statement = connection.prepareStatement(INSERT_INTO_INVOICES_NUMBERS);
            ((PreparedStatement) statement).setInt(1, getInvoiceNumber());
            ((PreparedStatement) statement).executeUpdate();
        }
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType_of_product() {
        return type_of_product;
    }

    public void setType_of_product(int type_of_product) {
        this.type_of_product = type_of_product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
