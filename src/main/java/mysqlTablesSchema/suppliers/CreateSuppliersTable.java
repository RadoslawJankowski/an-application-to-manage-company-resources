package mysqlTablesSchema.suppliers;

public class CreateSuppliersTable {

    /**
     * {@value SUPPLIER_TABLE}
     */
    public static final String SUPPLIER_TABLE = "CREATE TABLE IF NOT EXISTS suppliers ("
            + "   supplier_id INT AUTO_INCREMENT,"
            + "   name VARCHAR(30) NOT NULL,"
            + "   type_of_products INT NOT NULL,"
            + "   representative VARCHAR(70) NOT NULL,"
            + "   contact VARCHAR(11) NOT NULL,"
            + "   city VARCHAR(40) NOT NULL,"
            + "   address VARCHAR(70) NOT NULL,"
            + "   email VARCHAR(70) NOT NULL,"
            + "   PRIMARY KEY (supplier_id),"
            + "   FOREIGN KEY (type_of_products) REFERENCES types_of_products (type_id))";
}
