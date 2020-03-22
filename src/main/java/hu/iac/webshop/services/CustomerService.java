package hu.iac.webshop.services;


import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> list() {
        return this.customerRepository.findAll();
    }

    public Customer create(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Optional<Customer> find(Long id) {
        return this.customerRepository.findById(id);
    }

    public Customer update(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public boolean delete(Long id) {
        Optional<Customer> customer = this.customerRepository.findById(id);

        if (customer.isPresent()) {
            this.customerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
