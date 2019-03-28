package mysqlCommands;

public class SelectQueries {

    /**
     * {@value SELECT_ALL_FROM_EMPLOYEES}
     */
    public static final String SELECT_ALL_FROM_EMPLOYEES = "SELECT * FROM employees GROUP BY emp_id;";

    /**
     * {@value SELECT_ALL_FROM_SUPPLIERS}
     */
    public static final String SELECT_ALL_FROM_SUPPLIERS =  "SELECT * FROM suppliers GROUP BY supplier_id";
}
