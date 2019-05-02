package mysqlTablesSchema.companyDatabaseTables.types_of_products;

public class CreateTypesOfProductsTable {

    /**
     * {@value TYPES_OF_PRODUCTS_TABLE}
     */
    public static final String TYPES_OF_PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS types_of_products ("
            + "   type_id INT AUTO_INCREMENT,"
            + "   name VARCHAR (30) UNIQUE NOT NULL,"
            + "   PRIMARY KEY (type_id))";
}
