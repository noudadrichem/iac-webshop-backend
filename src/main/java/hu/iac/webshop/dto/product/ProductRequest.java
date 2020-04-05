package hu.iac.webshop.dto.product;

import java.util.List;

public class ProductRequest {

    private String name;
    private double price;
    private String description;
    private int stock;
    private List<Long> discountIds;

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

    public void setDiscountIds(List<Long> discountIds) {
        this.discountIds = discountIds;
    }

    public List<Long> getDiscountIds() {
        return this.discountIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
