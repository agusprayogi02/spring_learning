package io.agus.learning.controllers;

import io.agus.learning.dto.ResponseData;
import io.agus.learning.dto.SearchData;
import io.agus.learning.dto.SupplierData;
import io.agus.learning.models.entity.Supplier;
import io.agus.learning.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData item, Errors errs) {
        ResponseData<Supplier> response = new ResponseData<>();
        if (errs.hasErrors()) {
            for (ObjectError err : errs.getAllErrors()) {
                response.getMessage().add(err.getDefaultMessage());
            }
            response.setStatus(false);
            response.setPayload(null);
            return ResponseEntity.badRequest().body(response);
        }
        Supplier supplier = modelMapper.map(item, Supplier.class);
        response.setStatus(true);
        response.setPayload(service.save(supplier));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Iterable<Supplier> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Supplier findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody Supplier item, Errors errs) {
        ResponseData<Supplier> response = new ResponseData<>();
        if (errs.hasErrors()) {
            for (ObjectError err : errs.getAllErrors()) {
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

    @PostMapping(value = "/search/byEmail")
    public Supplier getMethodEmail(@RequestBody SearchData searchData) {
        return service.findByEmail(searchData.getSearchKey());
    }

    @PostMapping(value = "/search/byName")
    public List<Supplier> getMethodName(@RequestBody SearchData searchData) {
        return service.findByNameContains(searchData.getSearchKey());
    }

    @PostMapping(value = "/search/byEmailOrName")
    public List<Supplier> getMethodEmailOrName(@RequestBody SearchData searchData) {
        return service.findByEmailContainsOrNameContains(searchData.getSearchKey(), searchData.getOtherSearchKey());
    }

    @PostMapping(value = "/search/byNameStartsWith")
    public List<Supplier> getMethodNameStartsWith(@RequestBody SearchData searchData) {
        return service.findByNameStartsWith(searchData.getSearchKey());
    }
}
