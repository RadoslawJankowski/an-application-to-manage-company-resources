package mysqlTablesSchema.suppliers_products;

public class CreateSuppliersProductsTable {


    /**
     * {@value SUPPLIERS_PRODUCTS_TABLE}
     */
    public static final String SUPPLIERS_PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS suppliers_products (" +
            " product_id INT AUTO_INCREMENT," +
            " name VARCHAR (30) UNIQUE NOT NULL," +
            " product_type INT NOT NULL," +
            " price DECIMAL (10,2) NOT NULL," +
            " PRIMARY KEY (product_id)," +
            " FOREIGN KEY (product_type) REFERENCES types_of_products(type_id))";
}
