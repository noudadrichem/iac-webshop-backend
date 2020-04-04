package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.domain.Order;
import hu.iac.webshop.dto.product.OrderRequest;
import hu.iac.webshop.services.CustomerService;
import hu.iac.webshop.services.OrderService;
import hu.iac.webshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import javax.jms.Queue;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue orderQueue;

    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderController(OrderService orderService, CustomerService customerService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping("/authed/orders")
    public List<Order> get() {
        return this.orderService.list();
    }

    @GetMapping("/authed/orders/{id}")
    public Order getOrderById(@PathVariable Long id){
        return this.orderService.find(id).get();
    }

    @PostMapping("/authed/orders")
    public ResponseEntity create(@Valid @RequestBody OrderRequest orderRequest) throws MessageConversionException {
        Optional<Customer> customer = this.customerService.find(orderRequest.getCustomerId());

        if (customer.isPresent()) {
            Map<String, Object> orderMap = new HashMap<>();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = formatter.format(orderRequest.getDate());

            orderMap.put("date", strDate);
            orderMap.put("totalPrice", orderRequest.getTotalPrice());
            orderMap.put("customer_id", orderRequest.getCustomerId());

            jmsTemplate.convertAndSend(orderQueue, orderMap);

            return new ResponseEntity<>(orderMap, HttpStatus.OK);
        }

        return new ResponseEntity<>("Customer was not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/authed/orders/{id}")
    public ResponseEntity update(@Valid @RequestBody OrderRequest orderRequest, @PathVariable Long id) throws MessageConversionException {
        Optional<Customer> customer = this.customerService.find(orderRequest.getCustomerId());
        Optional<Order> optionalOrder = this.orderService.find(id);

        if (optionalOrder.isEmpty()) {
            return new ResponseEntity<>("Order was not found", HttpStatus.NOT_FOUND);
        }
        if (customer.isEmpty()) {
            return new ResponseEntity<>("Customer was not found", HttpStatus.NOT_FOUND);
        }

        Map<String, Object> orderMap = new HashMap<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(orderRequest.getDate());

        orderMap.put("id", id);
        orderMap.put("date", strDate);
        orderMap.put("totalPrice", orderRequest.getTotalPrice());
        orderMap.put("customer_id", orderRequest.getCustomerId());

        jmsTemplate.convertAndSend(orderQueue, orderMap);

        return new ResponseEntity<>(orderMap, HttpStatus.OK);
    }


    @DeleteMapping("/authed/orders/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        boolean isRemoved = this.orderService.delete(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
