package hu.iac.webshop.controllers;

import java.util.*;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hu.iac.webshop.domain.Discount;
import hu.iac.webshop.domain.Product;
import hu.iac.webshop.dto.product.ProductRequest;
import hu.iac.webshop.services.DiscountService;
import hu.iac.webshop.services.ProductService;

@RestController
public class ProductController {
    private final ProductService productService;
    private final DiscountService discountService;

    public ProductController(ProductService productService, DiscountService discountService) {
        this.productService = productService;
        this.discountService = discountService;
    }

    @GetMapping("/authed/products")
    public List<Product> getProducten() {
        List<Product> producten = this.productService.list();

        return producten;
    }

    @GetMapping("/authed/products/{id}")
    public ResponseEntity<Product> addProducten(@PathVariable Long id) {
        Optional<Product> product = productService.find(id);
        if (product.isEmpty()) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
    }

    @PostMapping("/authed/products")
    public Product addProducten(@RequestBody ProductRequest productRequest) {

        Product newProduct = new Product(
            productRequest.getName(),
            productRequest.getPrice(),
            productRequest.getStock()
        );

        for (Long discountId : productRequest.getDiscountIds()) {
            Optional<Discount> discount = discountService.findById(discountId);
            if(discount.isPresent()) {
                newProduct.addDiscount(discount.get());
            }
        }

        return this.productService.createProduct(newProduct);
    }


    @PutMapping("/authed/products/{id}")
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


    @DeleteMapping("/authed/products/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        boolean isProductRemoved = this.productService.delete(id);

        if (!isProductRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
