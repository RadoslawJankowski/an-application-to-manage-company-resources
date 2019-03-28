package mysqlCommands;

public class UpdateQueries {

    /**
     * {@value UPDATE_EMPLOYEE}
     */
    public static final String UPDATE_EMPLOYEE = "UPDATE employees SET firstName=?, " +
                                                 "lastName=?, " +
                                                 "birthDayDate=?, " +
                                                 "sex=?, " +
                                                 "empPosition=?, " +
                                                 "salary=? WHERE emp_id=?";

    /**
     * {@value UPDATE_SUPPLIER}
     */
    public static final String UPDATE_SUPPLIER = "UPDATE suppliers SET name=?, " +
                                                 "type_of_products=?, " +
                                                 "representative=?," +
                                                 "contact=?, " +
                                                 "city=?, " +
                                                 "address=?, " +
                                                 "email=? WHERE supplier_id=?";
}
