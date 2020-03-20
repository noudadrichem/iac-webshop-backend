package hu.iac.webshop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.iac.webshop.domain.Address;
import hu.iac.webshop.dto.product.AddressRequest;
import hu.iac.webshop.services.AddressService;

import java.util.List;

@RestController
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses")
    public List<Address> getAddresses() {
        return this.addressService.list();
    }

    @PostMapping("/address")
    public Address addAddress(@RequestBody AddressRequest addressRequest) {
        Address address = new Address(
                addressRequest.getStreet(),
                addressRequest.getCity(),
                addressRequest.getState(),
                addressRequest.getPostalCode(),
                addressRequest.getCountry()
        );

        return this.addressService.createAddress(address);
    }
}
