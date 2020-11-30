package org.example.onlinestore.domain.models;

import java.util.List;

public class CategoryModel {
    private Long id;
    private String name;
    private List<Long> attributes;
    private List<Long> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Long> attributes) {
        this.attributes = attributes;
    }

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }
}
