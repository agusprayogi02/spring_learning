package io.agus.learning.services;

import io.agus.learning.models.entity.Supplier;
import io.agus.learning.models.repo.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepo repo;

    public Supplier save(Supplier supplier) {
        return repo.save(supplier);
    }

    public Supplier findOne(Long id) {
        Optional<Supplier> item = repo.findById(id);
        return item.orElse(null);
    }

    public Iterable<Supplier> findAll() {
        return repo.findAll();
    }

    public void removeOne(Long id) {
        repo.deleteById(id);
    }
}
