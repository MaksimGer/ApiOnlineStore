package org.example.onlinestore.services.interfaces;

import org.example.onlinestore.domain.entityes.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    void save(Product product);
    Product update(Long id, String name);
    Product update(Product product);
    Product deleteById(Long id);
    void deleteAll();
    List<Product> findAllByName(String name);
}
