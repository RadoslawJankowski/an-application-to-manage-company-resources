package mysqlTablesSchema.ordersDatabaseTables.order_to_the_supplier;

import model.FinishedOrder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OrderToTheSupplier {

    public String getDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM_-yyyy:HH:mm:ss");
        String date = formatter.format(calendar.getTime());
        return date;
    }

    public static final String ORDER_TO_THE_SUPPLIER_TABLE = "CREATE TABLE IF NOT EXISTS order_"
            +FinishedOrder.getInstanceOfFinishedOrder().getInvoiceNumber() + " (" +
            " order_id INT AUTO_INCREMENT," +
            " invoice_id INT," +
            " supplier_name VARCHAR(30) NOT NULL," +
            " supplier_id INT NOT NULL, " +
            " product_id INT NOT NULL, " +
            " product_name VARCHAR(30) NOT NULL," +
            " product_price DECIMAL (10,2) NOT NULL," +
            " product_amount INT NOT NULL," +
            " sum DECIMAL (10,2) NOT NULL," +
            " status VARCHAR(10) DEFAULT 'NOT PAID'," +
            " order_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
            " PRIMARY KEY (order_id))";
}