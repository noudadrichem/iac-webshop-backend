package hu.iac.webshop.repositories;

import hu.iac.webshop.domain.OrderProduct;
import hu.iac.webshop.domain.OrderProductId;
import hu.iac.webshop.services.OrderProductService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
}
