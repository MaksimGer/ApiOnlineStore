package org.example.onlinestore.services.interfaces;

import org.example.onlinestore.domain.entityes.Attribute;

import java.util.List;

public interface IAttributeService {
    List<Attribute> findAll();
    Attribute findById(Long id);
    void save(Attribute attribute);
    Attribute update(Long id, String name);
    Attribute deleteById(Long id);
    void deleteAll();
}

