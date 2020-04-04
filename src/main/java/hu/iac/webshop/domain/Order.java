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
    private double totalPrice;
    private boolean isCheckedOut;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("order")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties("orders")
    private Customer customer;

    public Order() {
    }

    public Order(Date date, double totalPrice, Customer customer) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.isCheckedOut = false;
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCheckedOutTrue() {
        this.isCheckedOut = true;
    }

    public boolean isCheckedOut() {
        return this.isCheckedOut;
    }

   public void getCurrentOrderValue() {
       for (OrderProduct orderProduct : orderProducts) {
           totalPrice += orderProduct.getProduct().getPrice() * orderProduct.getAmount();
       }
   }

//    public void addProduct(OrderProduct orderProduct){
//        orderProducts.add(orderProduct);
//    }
}
