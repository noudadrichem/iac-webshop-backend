package hu.iac.webshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.iac.webshop.domain.Address;
import hu.iac.webshop.repositories.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> list() {
        return addressRepository.findAll();
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Optional<Address> find(Long id) {
        return this.addressRepository.findById(id);
    }

    public Address update(Address address) {
        return this.addressRepository.save(address);
    }

    public boolean delete(Long id) {
        Optional<Address> address = this.addressRepository.findById(id);

        if (address.isPresent()) {
            this.addressRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
