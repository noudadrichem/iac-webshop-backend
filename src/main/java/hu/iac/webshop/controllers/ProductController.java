package hu.iac.webshop.controllers;

import java.util.*;
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
            productRequest.getPrice()
        );

        return this.productService.createProduct(newProduct);
    }
}
