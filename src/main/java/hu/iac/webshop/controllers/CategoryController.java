package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Category;
import hu.iac.webshop.domain.Product;
import hu.iac.webshop.dto.product.CategoryRequest;
import hu.iac.webshop.services.CategoryService;
import hu.iac.webshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/authed/categories")
    public List<Category> getCategories() {
        return this.categoryService.list();
    }

    @GetMapping("/authed/categories/{id}")
    public ResponseEntity getCategory(@PathVariable Long id) {
        Optional<Category> optionalCategory = this.categoryService.find(id);

        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Category category = optionalCategory.get();
        System.out.println(category.getProducts());
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public Category create(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = new Category(
                categoryRequest.getImage(),
                categoryRequest.getName(),
                categoryRequest.getDescription()
        );

        return this.categoryService.create(category);
    }

    @PutMapping("/authed/categories/{id}")
    public ResponseEntity<Category> update(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable Long id) {
        Optional<Category> optionalCategory = this.categoryService.find(id);

        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Category category = optionalCategory.get();
        category.setImage(categoryRequest.getImage());
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        return new ResponseEntity<Category>(this.categoryService.update(category), HttpStatus.OK);
    }

    @PutMapping("/authed/categories/product/add/{id}")
    public ResponseEntity<Category> addProduct(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable Long id){
        Optional<Product> optionalProduct = this.productService.find(categoryRequest.getProductId());
        Optional<Category> optionalCategory = this.categoryService.find(id);

        if(optionalCategory.isEmpty() || optionalProduct.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Category category = optionalCategory.get();
        if (category.addProduct(optionalProduct.get()) == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Category>(this.categoryService.update(category), HttpStatus.OK);
    }

    @PutMapping("/authed/categories/product/remove/{id}")
    public ResponseEntity<Category> removeProduct(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable Long id){
        Optional<Product> optionalProduct = this.productService.find(categoryRequest.getProductId());
        Optional<Category> optionalCategory = this.categoryService.find(id);

        if(optionalCategory.isEmpty() || optionalProduct.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Category category = optionalCategory.get();
        if (category.removeProduct(optionalProduct.get()) == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Category>(this.categoryService.update(category), HttpStatus.OK);
    }

    @DeleteMapping("/authed/categories/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        boolean isRemoved = this.categoryService.delete(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
