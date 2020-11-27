package org.example.onlinestore.services.interfaces;

import org.example.onlinestore.domain.entityes.Attribute;

import java.util.List;
import java.util.Optional;

public interface IAttributeService {
    List<Attribute> findAll();
    Attribute findById(Long id);
    Attribute save(Attribute attribute);
    Attribute update(Long id, String name);
    Attribute deleteById(Long id);
    void deleteAll();
    List<Attribute> findAllByName(String attributeName);
}

