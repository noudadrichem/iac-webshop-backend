package hu.iac.webshop.domain;

import javax.persistence.*;

@Entity(name="Category")
public class Category {

    @Id
    @SequenceGenerator(name = "category_id_generator", sequenceName = "category_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_generator")
    private Long id;
    private String image;
    private String name;
    private String description;

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
}
