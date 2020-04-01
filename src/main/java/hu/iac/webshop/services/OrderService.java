package hu.iac.webshop.services;

import hu.iac.webshop.domain.Customer;
import hu.iac.webshop.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.iac.webshop.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> list() {
        return this.orderRepository.findAll();
    }

    public Order create(Order order) {
        return this.orderRepository.save(order);
    }

    public Optional<Order> find(Long id) {
        return this.orderRepository.findById(id);
    }

    public Order update(Order order) {
        return this.orderRepository.save(order);
    }

    public boolean delete(Long id) {
        Optional<Order> order = this.orderRepository.findById(id);

        if (order.isPresent()) {
            this.orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
