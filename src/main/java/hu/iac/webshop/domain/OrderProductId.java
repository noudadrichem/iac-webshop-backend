package hu.iac.webshop.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class OrderProductId implements Serializable {
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;

    private OrderProductId(){

    }

    public OrderProductId(Long orderId, Long productId){
        this.orderId = orderId;
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OrderProductId that = (OrderProductId) o;
        return Objects.equals(orderId, that.orderId) &&
            Objects.equals(productId, that.productId);
    }

}
