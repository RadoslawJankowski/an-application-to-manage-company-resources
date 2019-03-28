package mysqlTablesSchema;


public class TableModels {

    public static final String EMPLOYEE_TABLE = "CREATE TABLE IF NOT EXISTS employees ( "
            + "   emp_id INT AUTO_INCREMENT, "
            + "   firstName VARCHAR(30) NOT NULL, "
            + "   lastName VARCHAR(30) NOT NULL, "
            + "   birthDayDate DATE, "
            + "   sex VARCHAR(1), "
            + "   empPosition INT, "
            + "   salary DECIMAL(10,2),"
            + "   work_from TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
            + "   PRIMARY KEY (emp_id));";

    public static final String SUPPLIER_TABLE = "CREATE TABLE IF NOT EXISTS suppliers (" +
            " supplier_id INT AUTO_INCREMENT," +
            " name VARCHAR(30) NOT NULL," +
            " type_of_products INT NOT NULL," +
            " representative VARCHAR(70)," +
            " contact VARCHAR(11)," +
            " city VARCHAR(40)," +
            " address VARCHAR(70)," +
            " email VARCHAR(70)," +
            " PRIMARY KEY (supplier_id));";
}
