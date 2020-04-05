package hu.iac.webshop.repositories;

import hu.iac.webshop.domain.Order;
import hu.iac.webshop.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
