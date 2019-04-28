package mysqlCommands.updateQueries.suppliers;

public class UpdateSuppliers {

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
