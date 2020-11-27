package org.example.onlinestore.repos;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Parameter;
import org.example.onlinestore.domain.entityes.Product;
import org.example.onlinestore.domain.entityes.resources.CompositeKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ParameterRepo extends CrudRepository<Parameter, CompositeKey> {
    @NonNull
    List<Parameter> findAllByProduct(Product product);

    @NonNull
    List<Parameter> findAllByAttribute(Attribute attribute);

    @NonNull
    Optional<Parameter> findByProductAndAttribute(Product product, Attribute attribute);
}
