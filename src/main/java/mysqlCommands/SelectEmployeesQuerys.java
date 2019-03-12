package mysqlCommands;

public class SelectEmployeesQuerys {

    public static final String SELECT_ALL_FROM_EMPLOYEES = "SELECT * FROM employees GROUP BY emp_id;";

    public static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE emp_id =?";
}
