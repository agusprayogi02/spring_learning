package io.agus.learning.services;

import io.agus.learning.models.entity.Category;
import io.agus.learning.models.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepo repo;

    public Category save(Category item) {
        if (item.getId() != null) {
            Category current = findOne(item.getId());
            current.setName(item.getName());
            item = current;
        }
        return repo.save(item);
    }

    public Category findOne(Long id) {
        Optional<Category> item = repo.findById(id);
        return item.orElse(null);
    }

    public Iterable<Category> findAll() {
        return repo.findAll();
    }

    public void removeOne(Long id) {
        repo.deleteById(id);
    }

    public Iterable<Category> findByName(String name, Pageable pageable) {
        return repo.findByNameContains(name, pageable);
    }

    public Iterable<Category> saveBatch(Iterable<Category> items) {
        return repo.saveAll(items);
    }
}
