package hu.iac.webshop.repositories;

import hu.iac.webshop.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Serializable> {
}
