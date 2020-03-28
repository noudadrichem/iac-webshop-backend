package hu.iac.webshop.domain;

import javax.persistence.*;

@Entity(name = "Cart")
public class Cart {
//    @Id
//    @ManyToOne
//    @JoinColumn
    private Customer customer;

//    @Id
//    @ManyToOne
//    @JoinColumn
    private Product product;

    private int amount;

    public Cart(Customer customer, Product product, int amount){
        this.customer = customer;
        this.product = product;
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
