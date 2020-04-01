package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.*;
import hu.iac.webshop.dto.product.*;
import hu.iac.webshop.services.AddressService;
import hu.iac.webshop.services.CustomerService;
import hu.iac.webshop.services.OrderService;
import hu.iac.webshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CheckoutController {
    private final CustomerService customerService;
    private final AddressService addressService;
    private final OrderService orderService;
    private final ProductService productService;

    public CheckoutController(CustomerService customerService, AddressService addressService,  OrderService orderService, ProductService productService) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @PostMapping("/checkout")
    public ResponseEntity checkout(@Valid @RequestBody CheckoutRequest checkoutRequest) {





        Optional<Customer> newCustomer = this.customerService.find(checkoutRequest.getCustomerId());
        if (newCustomer.isEmpty()) {
            return new ResponseEntity<>("Customer id does not exist", HttpStatus.NOT_FOUND);
        }
        Customer customer = newCustomer.get();

        Address address = new Address(
            checkoutRequest.getAddressRequest().getStreet(),
            checkoutRequest.getAddressRequest().getCity(),
            checkoutRequest.getAddressRequest().getState(),
            checkoutRequest.getAddressRequest().getPostalCode(),
            checkoutRequest.getAddressRequest().getCountry(),
            customer
        );

        this.addressService.create(address);



        Optional<Order> optionalOrder = this.orderService.find(checkoutRequest.getOrderId());
        if (optionalOrder.isEmpty()) {
            return new ResponseEntity<>("Order id does not exist", HttpStatus.NOT_FOUND);
        } else  {
            Order order = optionalOrder.get();
            List<OrderProduct> orderProductList =  order.getOrderProducts();
            if (orderProductList.isEmpty()) {
                return new ResponseEntity<>("Order does not have any products", HttpStatus.NOT_ACCEPTABLE);
            } else {
                for (OrderProduct orderProduct : orderProductList) {
                    Product product = orderProduct.getProduct();
                    int amount = orderProduct.getAmount();
                    long productId = product.getId();
                    Optional<Product> optionalProduct = productService.find(productId);
                    if (optionalProduct.isEmpty()){
                        return new ResponseEntity<>("Not a valid product; id= " + productId, HttpStatus.NOT_FOUND);
                    } else {
                        Product savedProduct = optionalProduct.get();
                        int savedProductStock = savedProduct.getStock();
                        if (amount > savedProductStock) {
                            return new ResponseEntity<>("Not enought products in stock for product= " + productId, HttpStatus.NOT_ACCEPTABLE);
                        } else {
                            savedProduct.setStock(savedProductStock - amount);
                            productService.update(savedProduct);
                        }
                    }
                }
            }
        }


        String paymentMethod = checkoutRequest.getPaymentMethod();
        if (paymentMethod.equals("IDEAL")) {
            return new ResponseEntity<>("U heeft met IDEAL betaald", HttpStatus.OK);
        } else if (paymentMethod.equals("Paypal")) {
            return new ResponseEntity<>("U heeft met Paypal betaald", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Voer een geldig betaalmiddel in (IDEAL of Paypal)", HttpStatus.NOT_ACCEPTABLE);
        }



    }
}


