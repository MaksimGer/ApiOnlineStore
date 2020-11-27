package org.example.onlinestore.services.interfaces;

import org.example.onlinestore.domain.entityes.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(Long id);
    void save(Product product);
    Product update(Long id, String name);
    Product deleteById(Long id);
    void deleteAll();
}
