package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Cart;
import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.domain.Product;
import hu.iac.webshop.dto.product.CartRequest;
import hu.iac.webshop.dto.product.CustomerRequest;
import hu.iac.webshop.services.CartService;
import hu.iac.webshop.services.CustomerService;
import hu.iac.webshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.validation.Valid;

@RestController
public class CartController {


//
//    private final CartService cartService;
//    private final CustomerService customerService;
//    private final ProductService productService;
//
//
//    public CartController(CartService cartService, CustomerService customerService, ProductService productService) {
//        this.cartService = cartService;
//        this.customerService = customerService;
//        this.productService = productService;
//    }
//
//
//    @PostMapping("carts")
//    public ResponseEntity<Cart> addProduct(@Valid @RequestBody CartRequest cartRequest){
//        Optional<Customer> optionalCustomer = this.customerService.find(cartRequest.getCustomerId());
//        Optional<Product> optionalProduct = this.productService.find(cartRequest.getProductId());
//
//        if (optionalCustomer.isEmpty() || optionalProduct.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//            Cart cart = new Cart(
//            optionalCustomer.get(),
//            optionalProduct.get(),
//            cartRequest.getAmount()
//        );
//
//        return new ResponseEntity<Cart>(this.cartService.create(cart), HttpStatus.OK);
//    }
//
//    @PutMapping("carts")
//    public ResponseEntity<Cart> updateCart(@Valid @RequestBody CartRequest cartRequest){
//        Optional<Customer> optionalCustomer = this.customerService.find(cartRequest.getCustomerId());
//        Optional<Product> optionalProduct = this.productService.find(cartRequest.getProductId());
//        //Optional<Cart> optionalCart = this.cartService.find(optionalCustomer.get(), optionalProduct.get());
//
//        if (optionalCustomer.isEmpty() || optionalProduct.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        Customer customer = optionalCustomer.get();
//        customer.getCartProducts();
//        return new ResponseEntity<Cart>(, HttpStatus.OK);
//
//    }
//
//    @GetMapping("carts/{customerid}")
//    public ResponseEntity<Cart> getCartByCustomer(@PathVariable Long customerid){
//
//    }
//
//    @DeleteMapping("carts/{customerid}")
//    public ResponseEntity<Cart> emptyCart(@PathVariable Long customerid){
//
//    }
//
//    @DeleteMapping("carts")
//    public ResponseEntity<Cart> removeProduct(@Valid @RequestBody CartRequest cartRequest){
//
//    }


}
