package hu.iac.webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.iac.webshop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}