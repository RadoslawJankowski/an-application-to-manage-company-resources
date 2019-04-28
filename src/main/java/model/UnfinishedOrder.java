package model;

public class UnfinishedOrder {

    private int product_id;
    private String name;
    private int type_of_product;
    private double price;
    private int amount;
    private String supplier;
    private double sum;

    public UnfinishedOrder(int product_id, String name, int type_of_product, double price, int amount, String supplier, double sum) {
        this.product_id = product_id;
        this.name = name;
        this.type_of_product = type_of_product;
        this.price = price;
        this.amount = amount;
        this.supplier = supplier;
        this.sum = sum;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType_of_product() {
        return type_of_product;
    }

    public void setType_of_product(int type_of_product) {
        this.type_of_product = type_of_product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "UnfinishedOrder{" +
                "product_id=" + product_id +
                ", name='" + name + '\'' +
                ", type_of_product=" + type_of_product +
                ", price=" + price +
                ", amount=" + amount +
                ", supplier='" + supplier + '\'' +
                ", sum=" + sum +
                '}';
    }
}
