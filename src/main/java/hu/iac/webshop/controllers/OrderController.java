package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.Order;
import hu.iac.webshop.dto.product.OrderRequest;
import hu.iac.webshop.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/orders")
    public Order create(@Valid @RequestBody OrderRequest orderRequest) {
        Order order = new Order(
                orderRequest.getDate(),
                orderRequest.getTotalPrice()
            );

        return this.orderService.create(order);
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
