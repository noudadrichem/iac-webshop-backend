package hu.iac.webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.iac.webshop.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
