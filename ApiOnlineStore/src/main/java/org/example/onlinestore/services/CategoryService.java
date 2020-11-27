package org.example.onlinestore.services;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Category;
import org.example.onlinestore.repos.CategoryRepo;
import org.example.onlinestore.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(Long id) {
        if(id == null)
            return null;

        Optional<Category> categoryById = categoryRepo.findById(id);

        return categoryById.orElse(null);
    }

    @Override
    public void save(Category category) {
        if(category != null)
            categoryRepo.save(category);
    }

    @Override
    public Category update(Long id, String name) {
        if(id == null)
            return null;

        Category curCategory = categoryRepo.findById(id).orElse(null);

        if(curCategory == null)
            return null;

        curCategory.setName(name);

        return categoryRepo.save(curCategory);
    }

    @Override
    public Category deleteById(Long id) {
        if(id == null)
            return null;

        Category curCategory = categoryRepo.findById(id).orElse(null);

        if(curCategory != null)
            categoryRepo.delete(curCategory);

        return curCategory;
    }

    @Override
    public void deleteAll() {
        categoryRepo.deleteAll();
    }
}
