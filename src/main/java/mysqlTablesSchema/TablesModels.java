package mysqlTablesSchema;


public class TablesModels {

    /**
     * {@value EMPLOYEE_TABLE}
     */
    public static final String EMPLOYEE_TABLE = "CREATE TABLE IF NOT EXISTS employees ( "
            + "   emp_id INT AUTO_INCREMENT, "
            + "   firstName VARCHAR(30) NOT NULL, "
            + "   lastName VARCHAR(30) NOT NULL, "
            + "   birthDayDate DATE NOT NULL, "
            + "   sex VARCHAR(1) NOT NULL, "
            + "   empPosition INT NOT NULL, "
            + "   salary DECIMAL(10,2) NOT NULL,"
            + "   work_from TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
            + "   PRIMARY KEY (emp_id),"
            + "   FOREIGN KEY (empPosition) REFERENCES employee_positions(position_id))";

    /**
     * {@value EMPLOYEE_POSITIONS}
     */
    public static final String EMPLOYEE_POSITIONS = "CREATE TABLE IF NOT EXISTS employee_positions ("
            + "   position_id INT AUTO_INCREMENT,"
            + "   position_name VARCHAR(20) UNIQUE NOT NULL,"
            + "   PRIMARY KEY (position_id))";

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

    /**
     * {@value PRODUCT_TABLE}
     */
    public static final String PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS products ("
            + "   product_id INT AUTO_INCREMENT,"
            + "   type_of_product INT NOT NULL,"
            + "   name VARCHAR (30) NOT NULL,"
            + "   price DECIMAL (10,2) NOT NULL,"
            + "   PRIMARY KEY (product_id),"
            + "   FOREIGN KEY (type_of_product) REFERENCES types_of_products (type_id))";

    /**
     * {@value TYPES_OF_PRODUCTS}
     */
    public static final String TYPES_OF_PRODUCTS = "CREATE TABLE IF NOT EXISTS types_of_products ("
            + "   type_id INT AUTO_INCREMENT,"
            + "   name VARCHAR (30) UNIQUE NOT NULL,"
            + "   PRIMARY KEY (type_id))";
}
