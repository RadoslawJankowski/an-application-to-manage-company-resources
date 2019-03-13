package mysqlCommands.employeeCommands;

public class InsertIntoEmployeesQuerys {

    /**
     * {@value INSERT_INTO_EMPLOYEES}
     */
    public static String INSERT_INTO_EMPLOYEES = "INSERT INTO employees (firstName, lastName, birthDayDate, sex, empPosition, salary) " +
                                                 "VALUES (?,?,?,?,?,?)";

}
