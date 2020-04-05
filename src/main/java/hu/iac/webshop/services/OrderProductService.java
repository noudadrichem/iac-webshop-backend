package hu.iac.webshop.services;

import hu.iac.webshop.domain.OrderProduct;
import hu.iac.webshop.domain.OrderProductId;
import hu.iac.webshop.repositories.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderProductService {
    @Autowired
    private OrderProductRepository orderProductRepository;

    public List<OrderProduct> findAllByOrderId(Long orderId) {
        return this.orderProductRepository.findAllByOrderId(orderId);
    }

    public List<OrderProduct> list() {
        return this.orderProductRepository.findAll();
    }

    public OrderProduct create(OrderProduct orderProduct){
        return this.orderProductRepository.save(orderProduct);
    }

    public boolean delete(OrderProductId orderProductId) {
        Optional<OrderProduct> orderProduct = this.orderProductRepository.findById(orderProductId);

        if (orderProduct.isPresent()) {
            this.orderProductRepository.deleteById(orderProductId);
            return true;
        }

        return false;
    }
}
