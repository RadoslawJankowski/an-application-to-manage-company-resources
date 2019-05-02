package mysqlTablesSchema.companyDatabaseTables.employees;

public class CreateEmployeesTable {

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
}
