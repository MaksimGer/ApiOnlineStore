package org.example.onlinestore.services.interfaces;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Parameter;
import org.example.onlinestore.domain.entityes.Product;

import java.util.List;

public interface IParameterService{
    List<Parameter> findAllByProduct(Product product);
    List<Parameter> findAllByAttribute(Attribute attribute);
    Parameter findByProductAndAttribute(Product product, Attribute attribute);
    void save(Parameter parameter);
    Parameter update(Product product, Attribute attribute, String value);
    Parameter deleteByProductAndAttribute(Product product, Attribute attribute);
    void deleteAll();
    void deleteAll(List<Parameter> parameters);
}
