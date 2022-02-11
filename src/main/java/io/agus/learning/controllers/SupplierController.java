package io.agus.learning.controllers;

import io.agus.learning.dto.ResponseData;
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

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService service;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData item, Errors errs) {
        return getResponseDataResponseEntity(item, errs);
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
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierData item, Errors errs) {
        return getResponseDataResponseEntity(item, errs);
    }

    private ResponseEntity<ResponseData<Supplier>> getResponseDataResponseEntity(@RequestBody @Valid SupplierData item, Errors errs) {
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
}
