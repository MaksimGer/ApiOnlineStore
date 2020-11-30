package org.example.onlinestore.repos;

import org.example.onlinestore.domain.entityes.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface CategoryRepo extends CrudRepository<Category, Long> {
    @Override
    @NonNull
    List<Category> findAll();

    @NonNull
    List<Category> findAllByName(String categoryName);
}
