package mysqlCommands.updateQueries.employees;

public class UpdateEmployees {

    /**
     * {@value UPDATE_EMPLOYEE}
     */
    public static final String UPDATE_EMPLOYEE = "UPDATE employees SET firstName=?, " +
            "lastName=?, " +
            "birthDayDate=?, " +
            "sex=?, " +
            "empPosition=?, " +
            "salary=? WHERE emp_id=?";

}
