package mysqlTablesSchema.companyDatabaseTables.employee_positions;

public class CreateEmployeePositionsTable {

    /**
     * {@value EMPLOYEE_POSITIONS_TABLE}
     */
    public static final String EMPLOYEE_POSITIONS_TABLE = "CREATE TABLE IF NOT EXISTS employee_positions ("
            + "   position_id INT AUTO_INCREMENT,"
            + "   position_name VARCHAR(20) UNIQUE NOT NULL,"
            + "   PRIMARY KEY (position_id))";
}
