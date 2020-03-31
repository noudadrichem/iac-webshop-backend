package hu.iac.webshop.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "OrderProduct")
public class OrderProduct{
    @EmbeddedId
    private OrderProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @Column(name = "amount")
    private int amount;

    private OrderProduct(){

    }

    public OrderProduct(Order order, Product product, int amount){
        this.order = order;
        this.product = product;
        this.amount = amount;
        this.id = new OrderProductId(order.getId(), product.getId());
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
