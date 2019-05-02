package mysqlCommands.selectQueries.ordersDatabase.order_invoiceNumber;

import model.FinishedOrder;

public class SelectFromOrderInvoiceNumberQueries {

    public static final String SELECT_FROM_ORDER_INVOICE_NUMBER = "SELECT * FROM order_" + FinishedOrder.getInstanceOfFinishedOrder().getInvoiceNumber();
}
