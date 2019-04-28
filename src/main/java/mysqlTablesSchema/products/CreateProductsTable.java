package mysqlTablesSchema.products;

public class CreateProductsTable {

    /**
     * {@value PRODUCT_TABLE}
     */
    public static final String PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS products ("
            + "   product_id INT AUTO_INCREMENT,"
            + "   type_of_product INT NOT NULL,"
            + "   name VARCHAR (30) NOT NULL,"
            + "   price DECIMAL (10,2) NOT NULL,"
            + "   manufacturer INT NOT NULL,"
            + "   PRIMARY KEY (product_id),"
            + "   FOREIGN KEY (type_of_product) REFERENCES types_of_products (type_id),"
            + "   FOREIGN KEY (manufacturer) REFERENCES suppliers(supplier_id))";
}
