package hu.iac.webshop.dto.product;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CategoryRequest {
    private String image;

    @Length(max = 255)
    private String name;

    private String description;

    private Long productId;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
