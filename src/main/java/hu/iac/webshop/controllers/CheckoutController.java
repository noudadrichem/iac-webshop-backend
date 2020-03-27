package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Address;
import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.domain.Product;
import hu.iac.webshop.dto.product.AddressRequest;
import hu.iac.webshop.dto.product.CheckoutRequest;
import hu.iac.webshop.dto.product.CustomerRequest;
import hu.iac.webshop.services.AddressService;
import hu.iac.webshop.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CheckoutController {
    private final AddressService addressService;
    private final CustomerService customerService;

    public CheckoutController(AddressService addressService, CustomerService customerService) {
        this.addressService = addressService;
        this.customerService = customerService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<Void> checkout(@Valid @RequestBody AddressRequest addressRequest, CustomerRequest customerRequest, String paymentMethod, List<CheckoutRequest> productList) {
        Address address = new Address(
            addressRequest.getStreet(),
            addressRequest.getCity(),
            addressRequest.getState(),
            addressRequest.getPostalCode(),
            addressRequest.getCountry()
        );
        this.addressService.create(address);

        Customer customer = new Customer(
            customerRequest.getName(),
            customerRequest.getPhone(),
            customerRequest.getEmail()
        );
        this.customerService.create(customer);

        return new ResponseEntity<>(HttpStatus.OK);

    };
}
