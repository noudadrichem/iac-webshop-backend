package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.domain.Order;
import hu.iac.webshop.dto.product.OrderRequest;
import hu.iac.webshop.services.CustomerService;
import hu.iac.webshop.services.OrderService;
import hu.iac.webshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;


    public OrderController(OrderService orderService, CustomerService customerService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping("/orders")
    public List<Order> get() {
        return this.orderService.list();
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable Long id){
        return this.orderService.find(id).get();
    }

    @PostMapping("/orders")
    public ResponseEntity create(@Valid @RequestBody OrderRequest orderRequest) {
        Optional<Customer> customer = this.customerService.find(orderRequest.getCustomerId());

        if (customer.isPresent()) {
            Order order = new Order(orderRequest.getDate(), orderRequest.getTotalPrice(), customer.get());
            return new ResponseEntity<Order>(this.orderService.create(order), HttpStatus.OK);
        }

        return new ResponseEntity<>("Customer was not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> update(@Valid @RequestBody OrderRequest orderRequest, @PathVariable Long id) {
        Optional<Order> optionalOrder = this.orderService.find(id);

        if (optionalOrder.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Order order = optionalOrder.get();
        order.setDate(orderRequest.getDate());
        order.setTotalPrice(orderRequest.getTotalPrice());

        return new ResponseEntity<Order>(this.orderService.update(order), HttpStatus.OK);
    }


    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        boolean isRemoved = this.orderService.delete(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
