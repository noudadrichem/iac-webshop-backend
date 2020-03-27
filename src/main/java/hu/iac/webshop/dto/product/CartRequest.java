package hu.iac.webshop.dto.product;

import hu.iac.webshop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;

public class CartRequest  {
    @NotNull
    private Long productId;
    @NotNull
    private Long customerId;
    private int amount;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
