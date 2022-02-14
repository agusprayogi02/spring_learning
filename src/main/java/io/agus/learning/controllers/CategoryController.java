package io.agus.learning.controllers;

import io.agus.learning.dto.CategoryData;
import io.agus.learning.dto.ResponseData;
import io.agus.learning.dto.SearchData;
import io.agus.learning.models.entity.Category;
import io.agus.learning.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData item, Errors errs) {
        return getSave(item, errs);
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable("id") Long id) {
        return service.findOne(id);
    }

    @GetMapping
    public Iterable<Category> findAll() {
        return service.findAll();
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody Category item, Errors errs) {
        ResponseData<Category> response = new ResponseData<>();
        if(errs.hasErrors()){
            for(ObjectError err : errs.getAllErrors()){
                response.getMessage().add(err.getDefaultMessage());
            }
            response.setStatus(false);
            response.setPayload(null);
            return ResponseEntity.badRequest().body(response);
        }
        response.setStatus(true);
        response.setPayload(service.save(item));
        return ResponseEntity.ok(response);
    }

    private ResponseEntity<ResponseData<Category>> getSave(CategoryData item,
            Errors errs) {
        ResponseData<Category> response = new ResponseData<>();
        if (errs.hasErrors()) {
            for (ObjectError err : errs.getAllErrors()) {
                response.getMessage().add(err.getDefaultMessage());
            }
            response.setStatus(false);
            response.setPayload(null);
            return ResponseEntity.badRequest().body(response);
        }
        Category category = modelMapper.map(item, Category.class);
        response.setStatus(true);
        response.setPayload(service.save(category));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/searchName/{size}/{page}")
    public Iterable<Category> findByName(@RequestBody SearchData SearchData, @PathVariable("size") int size,
            @PathVariable("page") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return service.findByName(SearchData.getSearchKey(), pageable);
    }

    @PostMapping("/searchName/{size}/{page}/{sort}")
    public Iterable<Category> findByName(@RequestBody SearchData SearchData, @PathVariable("size") int size,
            @PathVariable("page") int page, @PathVariable("sort") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

        if (sort.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }
        return service.findByName(SearchData.getSearchKey(), pageable);
    }

    @PostMapping("/batch")
    public ResponseEntity<ResponseData<Iterable<Category>>> createBatch(@RequestBody Category[] categories) {
        ResponseData<Iterable<Category>> response = new ResponseData<>();
        response.setStatus(true);
        response.setPayload(service.saveBatch(Arrays.asList(categories)));
        return ResponseEntity.ok(response);
    }
}
