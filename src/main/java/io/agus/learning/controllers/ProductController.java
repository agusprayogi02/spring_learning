package io.agus.learning.controllers;

import io.agus.learning.dto.ResponseData;
import io.agus.learning.models.entity.Product;
import io.agus.learning.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product,
                                                        Errors err) {
        ResponseData<Product> rest = new ResponseData<>();
        if (err.hasErrors()) {
            for (ObjectError msg : err.getAllErrors()) {
                rest.getMessage().add(msg.getDefaultMessage());
            }
            rest.setStatus(false);
            rest.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rest);
        }
        rest.setStatus(true);
        rest.setPayload(service.save(product));
        return ResponseEntity.ok(rest);
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

    @PutMapping
    public ResponseEntity<ResponseData<Product>> updateById(@Valid @RequestBody Product product,
                                                            Errors err) {
        ResponseData<Product> rest = new ResponseData<>();

        if (err.hasErrors()) {
            for (ObjectError msg : err.getAllErrors()) {
                rest.getMessage().add(msg.getDefaultMessage());
            }
            rest.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rest);
        }
        rest.setStatus(true);
        rest.setPayload(service.save(product));
        return ResponseEntity.ok(rest);
    }
}
