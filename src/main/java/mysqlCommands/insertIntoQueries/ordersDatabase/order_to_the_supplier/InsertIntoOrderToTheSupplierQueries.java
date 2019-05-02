package mysqlCommands.insertIntoQueries.ordersDatabase.order_to_the_supplier;

import model.FinishedOrder;

public class InsertIntoOrderToTheSupplierQueries {

    public static final String INSERT_INTO_ORDER_TO_THE_SUPPLIER = "INSERT INTO order_" + FinishedOrder.getInstanceOfFinishedOrder().getInvoiceNumber() +  "(" +
            " invoice_id, supplier_name, supplier_id, product_id, product_name, product_price, product_amount, sum) VALUES (" +
            " ?,?,?,?,?,?,?,?)";
}
