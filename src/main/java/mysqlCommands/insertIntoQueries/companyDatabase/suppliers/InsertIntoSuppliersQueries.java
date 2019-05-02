package mysqlCommands.insertIntoQueries.companyDatabase.suppliers;

public class InsertIntoSuppliersQueries {

    /**
     * {@value INSERT_INTO_SUPPLIERS}
     */
    public static final String INSERT_INTO_SUPPLIERS = "INSERT INTO suppliers (name, type_of_products, representative, contact, city, address," +
            "email) VALUES (?,?,?,?,?,?,?)";

}
