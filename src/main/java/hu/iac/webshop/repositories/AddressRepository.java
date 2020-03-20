package hu.iac.webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.iac.webshop.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
