package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.dto.product.CustomerRequest;
import hu.iac.webshop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getCategories() {
        return this.customerService.list();
    }

    @PostMapping("/customer")
    public Map<String, Object> create(@Valid @RequestBody CustomerRequest customerRequest) throws MessageConversionException {
        Map<String, Object> customerMap = new HashMap<>();

        customerMap.put("name", customerRequest.getName());
        customerMap.put("phone", customerRequest.getPhone());
        customerMap.put("email", customerRequest.getEmail());

        jmsTemplate.convertAndSend(queue, customerMap);

        return customerMap;
    }

    @PutMapping("/category/{id}")
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

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        boolean isRemoved = this.customerService.delete(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
