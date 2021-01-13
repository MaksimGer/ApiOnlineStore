package org.example.onlinestore.services;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Product;
import org.example.onlinestore.repos.ProductRepo;
import org.example.onlinestore.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(Long id) {
        if(id == null)
            return null;

        Optional<Product> productById = productRepo.findById(id);

        return productById.orElse(null);
    }

    @Override
    public void save(Product product) {
        if(product != null)
            productRepo.save(product);
    }

    @Override
    public Product update(Long id, String name) {
        if(id == null)
            return null;

        Product curProduct = productRepo.findById(id).orElse(null);

        if(curProduct == null)
            return null;

        curProduct.setName(name);

        return productRepo.save(curProduct);
    }

    @Override
    public Product update(Product product) {
        Product curProduct = productRepo.findById(product.getId()).orElse(null);

        if(curProduct == null)
            return null;

        curProduct.setName(product.getName());
        curProduct.setPrice(product.getPrice());
        curProduct.setCount(product.getCount());
        curProduct.setCategory(product.getCategory());

        return productRepo.save(curProduct);
    }

    @Override
    public Product deleteById(Long id) {
        if(id == null)
            return null;

        Product curProduct = productRepo.findById(id).orElse(null);

        if(curProduct != null)
            productRepo.delete(curProduct);

        return curProduct;
    }

    @Override
    public void deleteAll() {
        productRepo.deleteAll();
    }

    @Override
    public List<Product> findAllByName(String name) {
        return productRepo.findAllByName(name);
    }
}
