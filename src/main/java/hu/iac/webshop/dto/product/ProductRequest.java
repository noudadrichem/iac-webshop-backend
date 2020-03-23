package hu.iac.webshop.dto.product;

import java.util.List;

public class ProductRequest {

    private String name;
    private double price;
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

    public void setDiscountIds(List<Long> discountIds) {
        this.discountIds = discountIds;
    }

    public List<Long> getDiscountIds() {
        return this.discountIds;
    }
}
