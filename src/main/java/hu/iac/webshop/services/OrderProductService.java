package hu.iac.webshop.services;

import hu.iac.webshop.domain.Discount;
import hu.iac.webshop.domain.OrderProduct;
import hu.iac.webshop.domain.OrderProductId;
import hu.iac.webshop.repositories.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderProductService {
    @Autowired
    private OrderProductRepository orderProductRepository;

    public OrderProduct create(OrderProduct orderProduct){
        return this.orderProductRepository.save(orderProduct);
    }

    public Optional<OrderProduct> find(Long orderId, Long productId) {
        OrderProductId orderProductId = new OrderProductId(orderId, productId);
        return this.orderProductRepository.findById(orderProductId);
    }

    public boolean deleteProduct(Long orderProductId){
        Optional<OrderProduct> orderProduct = this.orderProductRepository.findById(orderProductId);

        if (orderProduct.isPresent()) {
            this.orderProductRepository.deleteById(orderProductId);
            return true;
        } else {
            return false;
        }
    }
}
