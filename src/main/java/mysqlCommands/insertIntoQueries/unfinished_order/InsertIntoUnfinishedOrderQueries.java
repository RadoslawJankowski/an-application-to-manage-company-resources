package mysqlCommands.insertIntoQueries.unfinished_order;

public class InsertIntoUnfinishedOrderQueries {

    /**
     * {@value INSERT_PRODUCTS_INTO_UNFINISHED_ORDER}
     */
    public static final String INSERT_PRODUCTS_INTO_UNFINISHED_ORDER = "INSERT INTO unfinished_order (product_id, name, type_of_product, " +
            "price, amount, supplier, sum) VALUES (?,?,?,?,?,?,?)";

}
