package hu.iac.webshop.controllers;

import hu.iac.webshop.domain.*;
import hu.iac.webshop.dto.product.*;
import hu.iac.webshop.services.AddressService;
import hu.iac.webshop.services.CustomerService;
import hu.iac.webshop.services.OrderService;
import hu.iac.webshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class CheckoutController {
    private final CustomerService customerService;
    private final AddressService addressService;
    private final OrderService orderService;
    private final ProductService productService;

    public CheckoutController(CustomerService customerService, AddressService addressService,  OrderService orderService, ProductService productService) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @PostMapping("/authed/checkout")
    public ResponseEntity checkout(@Valid @RequestBody CheckoutRequest checkoutRequest) {
        Optional<Customer> newCustomer = this.customerService.find(checkoutRequest.getCustomerId());
        Optional<Order> optionalOrder = this.orderService.find(checkoutRequest.getOrderId());
        Optional<Address> optionalAddress = this.addressService.find(checkoutRequest.getCustomerId());

        if (newCustomer.isEmpty()) {
            return new ResponseEntity<>("Customer id does not exist", HttpStatus.NOT_FOUND);
        }
        if (optionalOrder.isEmpty()) {
            return new ResponseEntity<>("Order id does not exist", HttpStatus.NOT_FOUND);
        }
        if (optionalAddress.isEmpty()) {
            return new ResponseEntity<>("Address id does not exist", HttpStatus.NOT_FOUND);
        }

        Customer customer = newCustomer.get();
        Order order = optionalOrder.get();
        Address address = optionalAddress.get();


        List<OrderProduct> orderProductList =  order.getOrderProducts();
        if (orderProductList.isEmpty()) {
            return new ResponseEntity<>("Order does not have any products", HttpStatus.NOT_ACCEPTABLE);
        } else if (order.isCheckedOut()) {
            return new ResponseEntity<>("Order has already been processed", HttpStatus.NOT_ACCEPTABLE);
        }

        for (OrderProduct orderProduct : orderProductList) {
            Product product = orderProduct.getProduct();
            int amount = orderProduct.getAmount();
            int stock = product.getStock();
            if (amount > stock) {
                return new ResponseEntity<>("Not enough products in stock for product = " + product, HttpStatus.NOT_ACCEPTABLE);
            } else {
                product.setStock(stock - amount);
                productService.update(product);
            }
        }

        String paymentMethod = checkoutRequest.getPaymentMethod();
        if (paymentMethod.equals("IDEAL") || paymentMethod.equals("Paypal")) {
            order.setCheckedOutTrue();
            orderService.update(order);
            return new ResponseEntity<>("U heeft met betaald met  " + paymentMethod, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Voer een geldig betaalmiddel in (IDEAL of Paypal)", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}


