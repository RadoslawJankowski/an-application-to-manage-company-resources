package model;

import java.math.BigDecimal;

public class Product {

    private int product_id;
    private String name;
    private int product_type;
    private double price;

    public Product(int product_id, String name, int product_type, double price) {
        this.product_id = product_id;
        this.name = name;
        this.product_type = product_type;
        this.price = price;
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

    public int getProduct_type() {
        return product_type;
    }

    public void setProduct_type(int product_type) {
        this.product_type = product_type;
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
                "product_id=" + product_id +
                ", name='" + name + '\'' +
                ", product_type=" + product_type +
                ", price=" + price +
                '}';
    }
}
