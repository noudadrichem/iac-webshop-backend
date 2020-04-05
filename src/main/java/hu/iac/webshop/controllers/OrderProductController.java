package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.*;
import hu.iac.webshop.dto.product.OrderProductRequest;
import hu.iac.webshop.services.OrderProductService;
import hu.iac.webshop.services.OrderService;
import hu.iac.webshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderProductController {
    private final OrderProductService orderProductService;
    private final OrderService orderService;
    private final ProductService productService;

    public OrderProductController(OrderProductService orderProductService, OrderService orderService, ProductService productService){
        this.orderProductService = orderProductService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/orders/products")
    public List<OrderProduct> getAll() {
        return this.orderProductService.list();
    }

    @PostMapping("/orders/products")
    public ResponseEntity create(@Valid @RequestBody OrderProductRequest orderProductRequest){
        Optional<Order> order = this.orderService.find(orderProductRequest.getOrderId());
        Optional<Product> product = this.productService.find(orderProductRequest.getProductId());

        if (order.isPresent() && product.isPresent()) {
            int amount = orderProductRequest.getAmount();
            int stock = product.get().getStock();

            if (amount <= stock) {
                OrderProduct orderProduct = new OrderProduct(
                    order.get(),
                    product.get(),
                    orderProductRequest.getAmount()
                );
                return new ResponseEntity<OrderProduct>(this.orderProductService.create(orderProduct), HttpStatus.OK);
            }

            return new ResponseEntity<>("Not enough products in stock to order this amount", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/orders/{orderId}/products/{productId}")
    public ResponseEntity delete(@PathVariable Long orderId, @PathVariable Long productId) {
        OrderProductId orderProductId = new OrderProductId(orderId, productId);
        boolean isRemoved = this.orderProductService.delete(orderProductId);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(String.format("Product %s removed from order %s", productId, orderId), HttpStatus.OK);
    }

    @DeleteMapping("/orders/{orderId}/products")
    public ResponseEntity deleteAll(@PathVariable Long orderId) {
        List<OrderProduct> orderProducts = this.orderProductService.findAllByOrderId(orderId);

        if (orderProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (OrderProduct orderProduct : orderProducts) {
            boolean isRemoved = this.orderProductService.delete(orderProduct.getId());
            if (!isRemoved) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(String.format("All items cleared from order %s", orderId), HttpStatus.OK);
    }
}
