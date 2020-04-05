package hu.iac.webshop.listeners;

import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomerConsumer {

    @Autowired
    private CustomerService customerService;

    @JmsListener(destination = "customer.queue")
    public void customerListener(Map<String, Object> customerMap) {
        Customer customer = new Customer(
            customerMap.get("name").toString(),
            customerMap.get("phone").toString(),
            customerMap.get("email").toString()
        );

        if (customerMap.containsKey("id")) {
            customer.setId(Long.parseLong(customerMap.get("id").toString()));
        }

        this.customerService.create(customer);
    }
}
