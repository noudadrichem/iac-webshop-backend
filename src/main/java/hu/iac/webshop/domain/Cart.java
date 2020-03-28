package hu.iac.webshop.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Cart")
public class Cart implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn
    private Product product;

    private int amount;

    public Cart(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
