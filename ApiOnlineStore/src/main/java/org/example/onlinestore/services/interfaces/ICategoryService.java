package org.example.onlinestore.services.interfaces;

import org.example.onlinestore.domain.entityes.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(Category category);
    Category update(Category category);
    Category deleteById(Long id);
    void deleteAll();
    List<Category> findAllByName(String categoryName);
}
