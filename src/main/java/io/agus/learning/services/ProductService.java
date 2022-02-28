package io.agus.learning.services;

import io.agus.learning.models.entity.Product;
import io.agus.learning.models.entity.Supplier;
import io.agus.learning.models.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public Product findName(String name){
        return repo.findByName(name);
    }

    public List<Product> findByNameLike(@RequestBody String name){
        return repo.findByNameLike("%"+name+"%");
    }

    public List<Product> findByCategoryId(Long id){
        return repo.findByCategoryId(id);
    }

    public List<Product> findBySupplier(Supplier supplier){
        return repo.findBySupplier(supplier);
    }
}
