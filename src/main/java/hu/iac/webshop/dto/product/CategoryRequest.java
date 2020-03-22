package hu.iac.webshop.dto.product;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CategoryRequest {
    @NotNull
    private String image;
    @NotNull
    @Length(max = 255)
    private String name;
    @NotNull
    private String description;

    public CategoryRequest() {}

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
