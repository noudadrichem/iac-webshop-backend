package hu.iac.webshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hu.iac.webshop.domain.Address;
import hu.iac.webshop.dto.product.AddressRequest;
import hu.iac.webshop.services.AddressService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses")
    public List<Address> get() {
        return this.addressService.list();
    }

    @PostMapping("/addresses")
    public Address create(@Valid @RequestBody AddressRequest addressRequest) {
        Address address = new Address(
                addressRequest.getStreet(),
                addressRequest.getCity(),
                addressRequest.getState(),
                addressRequest.getPostalCode(),
                addressRequest.getCountry()
        );

        return this.addressService.create(address);
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<Address> update(@Valid @RequestBody AddressRequest addressRequest, @PathVariable Long id) {
        Optional<Address> optionalAddress = this.addressService.find(id);

        if (optionalAddress.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Address address = optionalAddress.get();
        address.setStreet(addressRequest.getStreet());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setCountry(addressRequest.getCountry());

        return new ResponseEntity<Address>(this.addressService.update(address), HttpStatus.OK);
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        boolean isRemoved = this.addressService.delete(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
