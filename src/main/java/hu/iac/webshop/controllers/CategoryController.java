package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Category;
import hu.iac.webshop.dto.product.CategoryRequest;
import hu.iac.webshop.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Category create(@RequestBody CategoryRequest categoryRequest) {
        Category category = new Category(
                categoryRequest.getImage(),
                categoryRequest.getName(),
                categoryRequest.getDescription()
        );

        return this.categoryService.create(category);
    }

    @PutMapping("/category/{id}")
    public Category update() {
        // TODO
        return new Category();
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        boolean isRemoved = this.categoryService.delete(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
