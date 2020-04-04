package hu.iac.webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.iac.webshop.domain.Category;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT * FROM category WHERE name = :name ", nativeQuery = true)
    Optional<Category> findByName(@Param("name") String name);
}
