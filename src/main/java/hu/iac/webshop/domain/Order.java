package hu.iac.webshop.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity(name = "Orderx")
public class Order {

    @Id
    @SequenceGenerator(name = "order_id_generator", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_generator")
    private Long id;
    private Date date;
    private double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public Order() {
    }

    public Order(Date date, double totalPrice, Customer customer) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.customer = customer;
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

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Customer getCustomer() {
        return this.customer;
    }

//    public void getCurrentOrderValue() {
//        for (OrderProduct product : orderProducts) {
//            totalPrice += product.getProduct().getPrice();
//        }
//    }

//    public void addProduct(OrderProduct orderProduct){
//        orderProducts.add(orderProduct);
//    }
}
