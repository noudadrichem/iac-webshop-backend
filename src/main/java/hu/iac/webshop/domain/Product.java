package hu.iac.webshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
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
    private int stock;

    @ManyToMany(fetch=FetchType.LAZY, mappedBy="products")
    @JsonIgnoreProperties("products")
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(fetch=FetchType.LAZY, mappedBy="products")
    @JsonIgnoreProperties("products")
    private List<Discount> discounts = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public Product() {}

    public Product(String name, double price, int stock, Category category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.categories.add(category);
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

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return this.stock;
    }

    public List<Discount> getDiscounts () {
            return this.discounts;
        }

        public void addDiscount (Discount discount){
            if (!discounts.contains(discount)) {
                discounts.add(discount);
            }
        }


}
