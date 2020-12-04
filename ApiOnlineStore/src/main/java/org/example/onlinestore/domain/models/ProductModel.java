package org.example.onlinestore.domain.models;

import java.util.HashMap;
import java.util.Map;

public class ProductModel {
    private Long id;
    private String name;
    private Long categoryId;
    private Map<Long, String> parameters = new HashMap<>();

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Map<Long, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<Long, String> parameters) {
        this.parameters = parameters;
    }
}
