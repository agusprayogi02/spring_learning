package io.agus.learning.services;

import io.agus.learning.models.entity.Category;
import io.agus.learning.models.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
        if (item.isPresent()) {
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
}
