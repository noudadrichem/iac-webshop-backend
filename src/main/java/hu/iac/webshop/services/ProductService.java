package hu.iac.webshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.iac.webshop.domain.Product;
import hu.iac.webshop.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> find(Long id) {
        return productRepository.findById(id);
    }

    public Product update(Product product) {
        return this.productRepository.save(product);
    }

    public boolean delete(Long id) {
        Optional<Product> product = this.productRepository.findById(id);

        if (product.isPresent()) {
            this.productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
