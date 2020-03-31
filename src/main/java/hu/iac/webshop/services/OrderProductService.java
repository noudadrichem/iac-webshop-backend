package hu.iac.webshop.services;

import hu.iac.webshop.domain.OrderProduct;
import hu.iac.webshop.repositories.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductService {
    @Autowired
    private OrderProductRepository orderProductRepository;

    public OrderProduct create(OrderProduct orderProduct){
        return this.orderProductRepository.save(orderProduct);
    }

}
