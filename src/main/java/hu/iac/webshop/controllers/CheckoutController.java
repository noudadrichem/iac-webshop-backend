package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Address;
import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.domain.Product;
import hu.iac.webshop.dto.product.*;
import hu.iac.webshop.services.AddressService;
import hu.iac.webshop.services.CustomerService;
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
    private final ProductService productService;

    public CheckoutController(CustomerService customerService, AddressService addressService, ProductService productService) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.productService = productService;
    }

    @PostMapping("/checkout")
    public ResponseEntity checkout(@Valid @RequestBody CheckoutRequest checkoutRequest) {


        Customer customer = new Customer(
            checkoutRequest.getCustomerRequest().getName(),
            checkoutRequest.getCustomerRequest().getPhone(),
            checkoutRequest.getCustomerRequest().getEmail()
        );


        Customer newCustomer = this.customerService.create(customer);

        Address address = new Address(
            checkoutRequest.getAddressRequest().getStreet(),
            checkoutRequest.getAddressRequest().getCity(),
            checkoutRequest.getAddressRequest().getState(),
            checkoutRequest.getAddressRequest().getPostalCode(),
            checkoutRequest.getAddressRequest().getCountry(),
            newCustomer
        );

        this.addressService.create(address);

        OrderRequest orderRequest = checkoutRequest.getOrderRequest();

//        ArrayList<OrderProducts> orderProductsList = orderRequest.getOrderProducts();
//        for (OrderProduct orderProduct : orderProductsList) {
//
//
//        }

        String paymentMethod = checkoutRequest.getPaymentMethod();
        if (paymentMethod.equals("IDEAL")) {
            return new ResponseEntity<>("U heeft met IDEAL betaald", HttpStatus.OK);
        } else if (paymentMethod.equals("Paypal")) {
            return new ResponseEntity<>("U heeft met Paypal betaald", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Voer een geldig betaalmiddel in (IDEAL of Paypal)", HttpStatus.NOT_ACCEPTABLE);
        }



    };
}



//    Optional<Product> optionalProduct = productService.find(product.getId());
//            if (optionalProduct.isEmpty()) {
//                return new ResponseEntity<>("Product met productid: " + product.getId() + " kon niet gevonden worden", HttpStatus.NOT_FOUND);
//    } else {
//    Product dbProduct = optionalProduct.get();
//    if (product.getStock() > dbProduct.getStock()) {
//    return new ResponseEntity<>("")
//    }
//    }
