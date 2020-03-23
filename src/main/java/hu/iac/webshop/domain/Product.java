package hu.iac.webshop.domain;

import java.util.List;

import javax.persistence.*;

@Entity(name="Product")
public class Product {

    @Id
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    private Long id;
    private String name;
    private double price;


    @ManyToMany(fetch=FetchType.LAZY, mappedBy="products")
    private List<Order> orders;

    @ManyToMany(fetch=FetchType.LAZY, mappedBy="products")
    private List<Discount> discounts;

    public Product() {}

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public List<Discount> getDiscounts() {
        return this.discounts;
    }

    public void addDiscount(Discount discount) {
        if (!discounts.contains(discount)) {
            discounts.add(discount);
        }
    }
}
