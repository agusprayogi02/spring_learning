package io.agus.learning.models.repo;

import io.agus.learning.models.entity.Supplier;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends CrudRepository<Supplier, Long> {

  // docs for jpa query methods :
  // https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods
  Supplier findByEmail(String email);

  List<Supplier> findByNameContains(String name);

  List<Supplier> findByEmailContainsOrNameContains(String email, String name);

  List<Supplier> findByNameStartsWith(String name);
}
