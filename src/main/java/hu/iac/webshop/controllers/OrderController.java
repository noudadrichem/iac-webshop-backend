package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Order;
import hu.iac.webshop.dto.product.OrderRequest;
import hu.iac.webshop.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> get() {
        return this.orderService.list();
    }

    @PostMapping("/order")
    public Order create(@RequestBody OrderRequest orderRequest) {
        Order order = new Order(
                orderRequest.getDate(),
                orderRequest.getTotalPrice()
            );

        return this.orderService.create(order);
    }

    @PutMapping("/order/{id}")
    public Order update() {
        // TODO
        return new Order();
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        boolean isRemoved = this.orderService.delete(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
