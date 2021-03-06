package hu.iac.webshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "OrderProduct")
public class OrderProduct {

    @EmbeddedId
    private OrderProductId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("orderId")
    @JsonIgnoreProperties("orderProducts")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    @JsonIgnoreProperties("orderProducts")
    private Product product;

    @Column(name = "amount")
    private int amount;

    public OrderProduct() {

    }

    public OrderProduct(Order order, Product product, int amount){
        this.order = order;
        this.product = product;
        this.amount = amount;
        this.id = new OrderProductId(order.getId(), product.getId());
    }

    public OrderProductId getId() {
        return id;
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
