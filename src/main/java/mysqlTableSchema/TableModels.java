package mysqlTableSchema;


public class TableModels {

    public static final String EMPLOYEE_TABLE = "CREATE TABLE IF NOT EXISTS employees ( "
            + "   emp_id INT AUTO_INCREMENT, "
            + "   firstName VARCHAR(30) NOT NULL, "
            + "   lastName VARCHAR(30) NOT NULL, "
            + "   birthDayDate VARCHAR(10), "
            + "   sex VARCHAR(1), "
            + "   empPosition INT, "
            + "   salary DECIMAL(10,2),"
            + "   work_from TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
            + "   PRIMARY KEY (emp_id));";
}
