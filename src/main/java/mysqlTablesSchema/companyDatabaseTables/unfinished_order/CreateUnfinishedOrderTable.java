package mysqlTablesSchema.companyDatabaseTables.unfinished_order;

public class CreateUnfinishedOrderTable {

    /**
     * {@value UNFINISHED_ORDER_TABLE}
     */
    public static final String UNFINISHED_ORDER_TABLE = "CREATE TABLE IF NOT EXISTS unfinished_order (" +
            " product_id INT," +
            " name VARCHAR (30), " +
            " type_of_product INT, " +
            " price DECIMAL (10,2), " +
            " amount INT NOT NULL," +
            " supplier VARCHAR (30)," +
            " supplier_id INT, " +
            " sum DECIMAL (10,2), " +
            " date_of_addition TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)";
}
