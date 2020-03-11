package hu.iac.webshop.controllers;

import java.util.*;
import org.springframework.web.bind.annotation.*;

import hu.iac.webshop.domain.Product;

@RestController
public class ProductController {

    // private final ProductenService ProductenService;

    public ProductController() {
    }

    @GetMapping("/producten")
    @ResponseBody
    public List<Product> getProducten() {
        List<Product> producten = new ArrayList<>();

        producten.add(
            new Product(1, "TV",100.5)
        );
        producten.add(
            new Product(2, "Sicke Computer", 1200.5)
        );

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
