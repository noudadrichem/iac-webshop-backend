package hu.iac.webshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity(name="Product")
public class Product {

    @Id
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;

    @ManyToMany(fetch=FetchType.LAZY, mappedBy="products")
    @JsonIgnoreProperties("products")
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(fetch=FetchType.LAZY, mappedBy="products")
    @JsonIgnoreProperties("products")
    private List<Discount> discounts = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("product")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @ManyToMany
    @JsonIgnoreProperties("products")
    private List<Category> categories;

    public Product() {}

    public Product(String name, double price, int stock, String description) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
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

    public double getOriginalPrice() {
        return price;
    }

    public double getPrice() {
        if (discounts.isEmpty()) {
            return price;
        } else {
            for (Discount discount : discounts) {
                Date curDate = new Date();
                if (curDate.before(discount.getEndDate()) && curDate.after(discount.getStartDate())) {
                    return discount.getDiscountedPrice();
                }
            }
        }
        return price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return this.stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPageUrl() {
        return "http://localhost:9091/products/" + this.id;
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
