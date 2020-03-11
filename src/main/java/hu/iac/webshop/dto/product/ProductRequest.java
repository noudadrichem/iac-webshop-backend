package hu.iac.webshop.dto.product;

public class ProductRequest {

    private String name;
    private double price;

    public ProductRequest() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
