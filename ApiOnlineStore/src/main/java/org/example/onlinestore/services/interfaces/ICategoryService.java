package org.example.onlinestore.services.interfaces;

import org.example.onlinestore.domain.entityes.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(Long id);
    void save(Category category);
    Category update(Long id, String name);
    Category deleteById(Long id);
    void deleteAll();
}
