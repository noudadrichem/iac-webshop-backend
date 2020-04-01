package hu.iac.webshop.dto.product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderProductRequest {
    @NotNull(message = "Please provide a product id.")
    private Long productId;

    @NotNull(message = "Please provide a order id.")
    private Long orderId;

    @Min(1)
    @NotNull(message = "Please provide a amount.")
    private int amount;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}


