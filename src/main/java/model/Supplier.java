package model;

public class Supplier {

    private int supplier_id;
    private String name;
    private int type_of_products;
    private String representative;
    private String contact;
    private String city;
    private String address;
    private String email;

    public Supplier(int supplier_id, String name, int type_of_products, String representative, String contact, String city, String address, String email) {
        this.supplier_id = supplier_id;
        this.name = name;
        this.type_of_products = type_of_products;
        this.representative = representative;
        this.contact = contact;
        this.city = city;
        this.address = address;
        this.email = email;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType_of_products() {
        return type_of_products;
    }

    public void setType_of_products(int type_of_products) {
        this.type_of_products = type_of_products;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplier_id=" + supplier_id +
                ", name='" + name + '\'' +
                ", type_of_products=" + type_of_products +
                ", representative='" + representative + '\'' +
                ", contact='" + contact + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
