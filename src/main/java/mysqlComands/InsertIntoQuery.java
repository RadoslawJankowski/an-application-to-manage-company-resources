package mysqlComands;

public class InsertIntoQuery {

    public static final String INSERT_INTO_EMPLOYEE_QUERY = "INSERT INTO employees (" +
            "firstName, " +
            "lastName, " +
            "birthDayDate, " +
            "sex, " +
            "empPosition, " +
            "salary " +
            ") VALUES ('" ;

    public static final String SEPARATE = "','";

    public static final String END_OF_INSERT_INTO_QUERY = "');";
}
