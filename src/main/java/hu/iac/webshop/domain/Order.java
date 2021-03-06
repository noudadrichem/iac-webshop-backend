package hu.iac.webshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    @Column(columnDefinition = "boolean default false")
    private boolean isCheckedOut;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("order")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties("orders")
    private Customer customer;

    public Order() {
    }

    public Order(Date date, Customer customer) {
        this.date = date;
        this.customer = customer;
//        this.isCheckedOut = false;
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

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }


     public void addProduct(OrderProduct orderProduct) {
//         if (this.products.contains(product)) {
             // UPDATE quantity in order of selected product, this was a brain fart.
             // int prodIdx = this.products.indexOf(product);
             // Product product = this.products.get(prodIdx);
             // product.
             // this.products.add(product);
//         } else {
             this.orderProducts.add(orderProduct);
//         }
     }

    public String getPageUrl() {
        return "http://localhost:9091/orders/" + this.id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCheckedOutTrue() {
        this.isCheckedOut = true;
    }

    public boolean isCheckedOut() {
        return this.isCheckedOut;
    }

    public double getTotalPrice() {
        double totalPrice = 0;

        for (OrderProduct orderProduct : orderProducts) {
            totalPrice += orderProduct.getProduct().getPrice() * orderProduct.getAmount();
        }

        return totalPrice;
    }

//    public void addProduct(OrderProduct orderProduct){
//        orderProducts.add(orderProduct);
//    }
}
