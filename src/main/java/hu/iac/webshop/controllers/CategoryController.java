package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Category;
import hu.iac.webshop.dto.product.CategoryRequest;
import hu.iac.webshop.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return this.categoryService.list();
    }

    @PostMapping("/category")
    public Category addCatagory(@RequestBody CategoryRequest categoryRequest) {
        Category category = new Category(
                categoryRequest.getImage(),
                categoryRequest.getName(),
                categoryRequest.getDescription()
        );

        return this.categoryService.createCategory(category);
    }
}
