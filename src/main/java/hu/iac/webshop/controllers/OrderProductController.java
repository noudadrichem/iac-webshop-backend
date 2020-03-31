package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.OrderProduct;
import hu.iac.webshop.domain.Order;
import hu.iac.webshop.domain.Product;
import hu.iac.webshop.dto.product.OrderProductRequest;
import hu.iac.webshop.services.OrderService;
import hu.iac.webshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

public class OrderProductController {
//    private final OrderService orderService;
//    private final ProductService productService;
//
//    public OrderProductController(OrderService orderService, ProductService productService){
//        this.orderService = orderService;
//        this.productService = productService;
//    }
//
//    @PostMapping("/orderproducts")
//    public ResponseEntity<OrderProduct> create(@Valid @RequestBody OrderProductRequest orderProductRequest){
//        Optional<Order> order = this.orderService.find(orderProductRequest.getOrderId());
//        Optional<Product> product = this.productService.find(orderProductRequest.getProductId());
//
//        if (order.isPresent() && product.isPresent()) {
//            OrderProduct orderProduct = new OrderProduct(
//                order.get(),
//                product.get(),
//                orderProductRequest.getAmount()
//            );
//            return new ResponseEntity<OrderProduct>(this.orderService.addProduct(orderProduct), HttpStatus.OK);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
