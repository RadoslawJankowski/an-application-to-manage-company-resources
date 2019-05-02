package mysqlTablesSchema.ordersDatabaseTables.invoices_numbers;

public class InvoicesNumbers {

    public static final String INVOICES_NUMBERS_TABLE = "CREATE TABLE IF NOT EXISTS invoices_numbers (invoice_number INT UNIQUE," +
            " date_of_create TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)";
}
