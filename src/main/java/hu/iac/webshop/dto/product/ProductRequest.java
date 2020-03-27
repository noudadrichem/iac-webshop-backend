package hu.iac.webshop.dto.product;

public class ProductRequest {

    private String name;
    private double price;
    private int stock;

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

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return this.stock;
    }
}
