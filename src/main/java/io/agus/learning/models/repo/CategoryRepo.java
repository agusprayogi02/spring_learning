package io.agus.learning.models.repo;

import io.agus.learning.models.entity.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends PagingAndSortingRepository<Category, Long> {

  Page<Category> findByNameContains(String name, Pageable page);
}
