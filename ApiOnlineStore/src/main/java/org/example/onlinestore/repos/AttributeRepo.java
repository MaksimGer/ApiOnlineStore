package org.example.onlinestore.repos;

import org.example.onlinestore.domain.entityes.Attribute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface AttributeRepo extends CrudRepository<Attribute, Long> {
    @Override
    @NonNull
    List<Attribute> findAll();

    @NonNull
    List<Attribute> findAllByName(String attributeName);
}
