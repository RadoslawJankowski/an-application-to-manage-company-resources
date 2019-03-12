package mysqlCommands;

public class UpdateEmployeeQuery {

    public static final String UPDATE_EMPLOYEE = "UPDATE employees SET firstName=?, " +
                                                 "lastName=?, " +
                                                 "birthDayDate=?, " +
                                                 "sex=?, " +
                                                 "empPosition=?, " +
                                                 "salary=? WHERE emp_id=?";
}
