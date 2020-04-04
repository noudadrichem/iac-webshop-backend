package hu.iac.webshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.iac.webshop.domain.Category;
import hu.iac.webshop.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> list() {
        return this.categoryRepository.findAll();
    }

    public Optional<Category> findByName(String name){
        return this.categoryRepository.findByName(name);
    }

    public Category create(Category category) {
        return this.categoryRepository.save(category);
    }

    public Optional<Category> find(Long id) {
        return this.categoryRepository.findById(id);
    }

    public Category update(Category category) {
        return this.categoryRepository.save(category);
    }

    public boolean delete(Long id) {
        Optional<Category> category = this.categoryRepository.findById(id);

        if (category.isPresent()) {
            this.categoryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
