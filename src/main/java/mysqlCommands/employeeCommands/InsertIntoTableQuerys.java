package mysqlCommands.employeeCommands;

public class InsertIntoTableQuerys {

    /**
     * {@value INSERT_INTO_EMPLOYEES}
     */
    public static String INSERT_INTO_EMPLOYEES = "INSERT INTO employees (firstName, lastName, birthDayDate, sex, empPosition, salary) " +
                                                 "VALUES (?,?,?,?,?,?)";

    /**
     * {@value INSERT_INTO_SUPPLIERS}
     */
    public static String INSERT_INTO_SUPPLIERS = "INSERT INTO suppliers (name, type_of_products, representative, contact, city, address," +
            "email) VALUES (?,?,?,?,?,?,?)";
}
