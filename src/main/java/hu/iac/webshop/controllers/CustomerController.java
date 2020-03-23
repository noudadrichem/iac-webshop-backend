package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.dto.product.CategoryRequest;
import hu.iac.webshop.dto.product.CustomerRequest;
import hu.iac.webshop.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getCategories() {
        return this.customerService.list();
    }

    @PostMapping("/customers")
    public Customer create(@Valid @RequestBody CustomerRequest customerRequest){
        Customer customer = new Customer(
            customerRequest.getName(),
            customerRequest.getPhone(),
            customerRequest.getEmail()
        );

        return this.customerService.create(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> update(@Valid @RequestBody CustomerRequest customerRequest, @PathVariable Long id) {
        Optional<Customer> optionalCustomer = this.customerService.find(id);

        if (optionalCustomer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Customer customer = optionalCustomer.get();
        customer.setName(customerRequest.getName());
        customer.setPhone(customerRequest.getPhone());
        customer.setEmail(customerRequest.getEmail());

        return new ResponseEntity<Customer>(this.customerService.update(customer), HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        boolean isRemoved = this.customerService.delete(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
