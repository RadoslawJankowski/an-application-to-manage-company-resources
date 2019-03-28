package model;

public class Product {

    private int productId;
    private int typeOfProduct;
    private String name;
    private double price;

    public Product(int productId, int typeOfProduct,  String name, double price) {
        this.productId = productId;
        this.typeOfProduct = typeOfProduct;
        this.name = name;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(int typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", typeOfProduct=" + typeOfProduct +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
