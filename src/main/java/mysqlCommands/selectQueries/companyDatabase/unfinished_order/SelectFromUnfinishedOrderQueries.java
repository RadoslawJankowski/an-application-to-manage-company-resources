package mysqlCommands.selectQueries.companyDatabase.unfinished_order;

public class SelectFromUnfinishedOrderQueries {

    /**
     * {@value SELECT_PRICE_AND_AMOUNT_FROM_UNFINISHED_ORDER}
     */
    public static final String SELECT_PRICE_AND_AMOUNT_FROM_UNFINISHED_ORDER = "SELECT price, amount FROM unfinished_order";

    /**
     * {@value SELECT_ID_NAME_PRICE_AMOUNT_TYPE_SUPPLIER_SUM_FROM_UNFINISHED_ORDER}
     */
    public static final String SELECT_ID_NAME_PRICE_AMOUNT_TYPE_SUPPLIER_SUM_FROM_UNFINISHED_ORDER = "SELECT product_id, name, price, amount, type_of_product, supplier, sum FROM unfinished_order";

    /**
     * {@value SELECT_ALL_FROM_UNFINISHED_ORDER}
     */
    public static final String SELECT_ALL_FROM_UNFINISHED_ORDER = "SELECT * FROM unfinished_order";

    /**
     * {@value SELECT_PRODUCT_ID_COUNT_FROM_UNFINISHED_ORDER}
     */
    public static final String SELECT_PRODUCT_ID_COUNT_FROM_UNFINISHED_ORDER = "SELECT COUNT(product_id) FROM UNFINISHED_ORDER";
}
