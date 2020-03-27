package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Address;
import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.domain.Product;
import hu.iac.webshop.dto.product.AddressRequest;
import hu.iac.webshop.dto.product.CheckoutRequest;
import hu.iac.webshop.dto.product.CustomerRequest;
import hu.iac.webshop.dto.product.ProductsCheckoutRequest;
import hu.iac.webshop.services.AddressService;
import hu.iac.webshop.services.CustomerService;
import hu.iac.webshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
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

//        System.out.println(customer);

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

        List<ProductsCheckoutRequest> productList = checkoutRequest.getProductsCheckoutRequests();

        for (ProductsCheckoutRequest productsCheckoutRequest : productList) {
            Optional<Product> optionalProduct = productService.find(productsCheckoutRequest.getProductId());
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                if (product.getStock() - productsCheckoutRequest.getAmount() < 0) {
                    return new ResponseEntity<>("Er is niet genoeg voorraad", HttpStatus.NOT_ACCEPTABLE);
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



    };
}
