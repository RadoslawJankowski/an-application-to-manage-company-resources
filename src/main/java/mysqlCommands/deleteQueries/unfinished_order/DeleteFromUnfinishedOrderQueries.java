package mysqlCommands.deleteQueries.unfinished_order;

public class DeleteFromUnfinishedOrderQueries {

    /**
     * {@value DELETE_FROM_UNFINISHED_ORDER_BY_ID}
     */
    public static final String DELETE_FROM_UNFINISHED_ORDER_BY_ID = "DELETE FROM unfinished_order WHERE product_id=?";

    public static final String DELETE_ALL_FROM_UNFINISHED_ORDER = "DELETE FROM unfinished_order WHERE product_id > 0";
}
