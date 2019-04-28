package mysqlCommands.insertIntoQueries.employees;

public class InsertIntoEmployeesQueries {

    /**
     * {@value INSERT_INTO_EMPLOYEES}
     */
    public static final String INSERT_INTO_EMPLOYEES = "INSERT INTO employees (firstName, lastName, birthDayDate, sex, empPosition, salary) " +
            "VALUES (?,?,?,?,?,?)";
}
