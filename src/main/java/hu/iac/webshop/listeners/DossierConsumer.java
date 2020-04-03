package hu.iac.webshop.listeners;

import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.domain.Order;
import hu.iac.webshop.services.CustomerService;
import hu.iac.webshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class DossierConsumer {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @JmsListener(destination = "order.queue")
    public void orderListener(Map<String, Object> orderMap) throws ParseException {
        Customer customer =  this.customerService.find(Long.parseLong(orderMap.get("customer_id").toString())).get();

        Order order = new Order(
            new SimpleDateFormat("yyyy-MM-dd").parse(orderMap.get("date").toString()),
            Double.parseDouble(orderMap.get("totalPrice").toString()),
            customer
        );

        if (orderMap.containsKey("id")) {
            order.setId(Long.parseLong(orderMap.get("id").toString()));
        }

        this.orderService.create(order);
    }
}
