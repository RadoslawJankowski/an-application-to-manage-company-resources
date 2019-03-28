package mysqlCommands.employeeCommands;

public class DeleteQueries {

    /**
     * {@value DELETE_EMPLOYEE_BY_ID}
     */
    public static final String DELETE_EMPLOYEE_BY_ID = "DELETE FROM employees WHERE emp_id =?";

    /**
     * {@value DELETE_SUPPLIER_BY_ID}
     */
    public static final String DELETE_SUPPLIER_BY_ID = "DELETE FROM suppliers WHERE supplier_id =?";
}
