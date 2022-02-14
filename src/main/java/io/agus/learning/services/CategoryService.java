package io.agus.learning.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.agus.learning.models.entity.Category;
import io.agus.learning.models.repo.CategoryRepo;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepo repo;

    public Category save(Category item) {
        return repo.save(item);
    }

    public Category findOne(Long id) {
        Optional<Category> item = repo.findById(id);
        if (!item.isPresent()) {
            return null;
        }
        return item.get();
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
