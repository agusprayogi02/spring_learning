package io.agus.learning.models.repo;

import io.agus.learning.models.entity.Product;
import io.agus.learning.models.entity.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {
    List<Product> findByNameContains(String name);

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product findByName(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    List<Product> findByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id = :id")
    List<Product> findByCategoryId(@PathParam("id") Long id);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    List<Product> findBySupplier(Supplier supplier);
}
