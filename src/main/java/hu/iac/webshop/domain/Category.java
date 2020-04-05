package hu.iac.webshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name="Category")
public class Category {

    @Id
    @SequenceGenerator(name = "category_id_generator", sequenceName = "category_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_generator")
    private Long id;
    private String image;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
        name = "product_category",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnoreProperties("categories")
    private List<Product> products;

    public Category() {}

    public Category(String image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean addProduct(Product product){
        if(!products.contains(product)) {
            this.products.add(product);
            return true;
        }

        return false;
    }

    public boolean removeProduct(Product product) {
        if (products.contains(product)) {
            this.products.remove(product);
            return true;
        }

        return false;
    }

    public String getPageUrl() {
        return "http://localhost:9091/categories/" + this.id;
    }
}
