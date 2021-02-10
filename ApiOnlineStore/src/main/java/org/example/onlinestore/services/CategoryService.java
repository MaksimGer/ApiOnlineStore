package org.example.onlinestore.services;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Category;
import org.example.onlinestore.exceptions.BadRequestException;
import org.example.onlinestore.exceptions.NotFoundException;
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
    public Optional<Category> findById(Long id) {
        if(id != null) {
            return categoryRepo.findById(id);
        }else{
            throw new BadRequestException("CategoryId is null");
        }
    }

    @Override
    public Category save(Category category) {
        if(category != null) {
            return categoryRepo.save(category);
        }else{
            throw new BadRequestException("Category is null");
        }
    }

    @Override
    public Category update(Category updateCategory) {
        if(updateCategory.getId() != null) {
            Category curCategory = categoryRepo.findById(updateCategory.getId()).orElseThrow(
                    () -> new BadRequestException("Category does not exist")
            );

            curCategory.setName(updateCategory.getName());
            curCategory.setAttributes(updateCategory.getAttributes());

            return categoryRepo.save(curCategory);
        }else {
            throw new BadRequestException("CategoryId is null");
        }
    }

    @Override
    public Category deleteById(Long id) {
        if(id != null) {
            Category curCategory = categoryRepo.findById(id).orElseThrow(NotFoundException::new);

            categoryRepo.delete(curCategory);

            return curCategory;
        }else {
            throw new BadRequestException("CategoryId is null");
        }
    }

    @Override
    public void deleteAll() {
        categoryRepo.deleteAll();
    }

    @Override
    public List<Category> findAllByName(String categoryName) {
        if(categoryName != null) {
            return categoryRepo.findAllByName(categoryName);
        }else{
            throw new BadRequestException("CategoryName is null");
        }
    }
}
