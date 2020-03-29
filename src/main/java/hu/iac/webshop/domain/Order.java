package hu.iac.webshop.domain;

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

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Product> products;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        if (this.products.contains(product)) {
            // UPDATE quantity in order of selected product, this was a brain fart.
            // int prodIdx = this.products.indexOf(product);
            // Product product = this.products.get(prodIdx);
            // product.
            // this.products.add(product);
        } else {
            this.products.add(product);
        }
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public double getCurrentOrderValue() {
        for (Product product : products) {
            this.totalPrice += product.getPrice();
        }

        return totalPrice;
    }
}
