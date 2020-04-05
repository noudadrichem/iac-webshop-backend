package hu.iac.webshop.dto.product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProductRequest {
    @NotNull(message = "Please provide a name")
    private String name;

    @NotNull(message = "Please provide a price")
    @Min(0)
    private double price;

    @NotNull(message = "Please provide a description")
    private String description;

    @NotNull(message = "Please provide a stock")
    @Min(0)
    private int stock;
    private List<Long> discountIds;
    private Long categoryId;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
