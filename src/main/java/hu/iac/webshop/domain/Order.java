package hu.iac.webshop.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name="Orderx")
public class Order {

    @Id
    @SequenceGenerator(name = "order_id_generator", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_generator")
    private Long id;
    private Date date;
    private double totalPrice;

//    @ManyToOne
//    @JoinColumn(name="customer_id", nullable=false)
//    private Customer customer;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Product> products;

    public Order() {
    }

    public Order(Date date, double totalPrice) {
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }

//    public void getCurrentOrderValue() {
//        for (Product product : products) {
//            totalPrice += product.getPrice();
//        };
//    }
}
