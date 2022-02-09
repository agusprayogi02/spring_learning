package io.agus.learning.controllers;

import io.agus.learning.models.entity.Product;
import io.agus.learning.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.removeOne(id);
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Long id) {
        return service.findOne(id);
    }

    @PostMapping("/{id}")
    public Product updateById(@RequestBody Product product, @PathVariable("id") Long id) {
        product.setId(id);
        return service.save(product);
    }
}
