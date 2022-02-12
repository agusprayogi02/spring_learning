package io.agus.learning.services;

import io.agus.learning.models.entity.Product;
import io.agus.learning.models.entity.Supplier;
import io.agus.learning.models.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public Product save(Product product) {
        return repo.save(product);
    }

    public Product findOne(Long id) {
        return repo.findById(id).get();
    }

    public Iterable<Product> findAll() {
        return repo.findAll();
    }

    public void removeOne(Long id) {
        repo.deleteById(id);
    }

    public List<Product> findByName(String name) {
        return repo.findByNameContains(name);
    }

    public void addSupplier(Supplier item, Long productId) {
        Product product = findOne(productId);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        product.getSuppliers().add(item);
        save(product);
    }
}
