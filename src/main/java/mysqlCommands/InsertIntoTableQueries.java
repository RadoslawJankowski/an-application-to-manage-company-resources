package mysqlCommands;

public class InsertIntoTableQueries {

    /**
     * {@value INSERT_INTO_EMPLOYEES}
     */
    public static final String INSERT_INTO_EMPLOYEES = "INSERT INTO employees (firstName, lastName, birthDayDate, sex, empPosition, salary) " +
                                                 "VALUES (?,?,?,?,?,?)";

    /**
     * {@value INSERT_INTO_SUPPLIERS}
     */
    public static final String INSERT_INTO_SUPPLIERS = "INSERT INTO suppliers (name, type_of_products, representative, contact, city, address," +
            "email) VALUES (?,?,?,?,?,?,?)";

    /**
     * {@value BASIC_TYPES_OF_PRODUCTS}
     */
    public static final String BASIC_TYPES_OF_PRODUCTS = "INSERT INTO types_of_products (name) VALUES " +
            "(?)";

    /**
     * {@value BASIC_EMPLOYEE_POSITIONS}
     */
    public static final String BASIC_EMPLOYEE_POSITIONS = "INSERT INTO employee_positions (position_name) VALUES (?)";
}
