package hu.iac.webshop.controllers;

import java.util.*;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hu.iac.webshop.domain.Product;
import hu.iac.webshop.dto.product.ProductRequest;
import hu.iac.webshop.services.ProductService;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/producten")
    public List<Product> getProducten() {
        List<Product> producten = this.productService.list();

        return producten;
    }

    @PostMapping("/producten/new")
    public Product addProducten(@RequestBody ProductRequest productRequest) {

        Product newProduct = new Product(
            productRequest.getName(),
            productRequest.getPrice(),
            productRequest.getStock()
        );

        return this.productService.createProduct(newProduct);
    }

    @PutMapping("/producten/{id}/update")
    public ResponseEntity<Product> update(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long id) {
        Optional<Product> optionalProduct = this.productService.find(id);

        if (optionalProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Product product = optionalProduct.get();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());

        Product updatedProduct = this.productService.update(product);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/producten/{id}/delete")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        boolean isProductRemoved = this.productService.delete(id);

        if (!isProductRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
