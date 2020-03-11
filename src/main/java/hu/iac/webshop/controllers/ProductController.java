package hu.iac.webshop.controllers;

import java.util.*;
import org.springframework.web.bind.annotation.*;

import hu.iac.webshop.domain.Product;
import hu.iac.webshop.services.ProductService;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/producten")
    @ResponseBody
    public List<Product> getProducten() {
        List<Product> producten = this.productService.list();

        return producten;
    }

    // @GetMapping("/producten/{eventId}")
    // public Response getProductenenByEventId(@PathVariable Long eventId) {
    //     List<Producten> Productenen = ProductenService.getProductenenByEventId(eventId);

    //     return new Response(Productenen, null);
    // }

    // @PostMapping("/producten")
    // public Response addProducten(@RequestBody InschrijfRequest inschrijfRequest) {

    //     Producten Producten = ProductenService.addProducten(inschrijfRequest);

    //     return new Response(Producten, null);

    // }
}
