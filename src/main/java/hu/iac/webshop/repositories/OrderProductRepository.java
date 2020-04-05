package hu.iac.webshop.repositories;

import hu.iac.webshop.domain.OrderProduct;
import hu.iac.webshop.domain.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
    @Query(value = "SELECT p FROM OrderProduct p WHERE p.order.id = :orderId")
    List<OrderProduct> findAllByOrderId(@Param("orderId") Long orderId);
}
