package org.example.onlinestore.repos;

import org.example.onlinestore.domain.entityes.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {
    @Override
    @NonNull
    List<Product> findAll();
    List<Product> findAllByName(String name);
}
