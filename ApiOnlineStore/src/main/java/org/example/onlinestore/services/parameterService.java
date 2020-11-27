package org.example.onlinestore.services;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Parameter;
import org.example.onlinestore.domain.entityes.Product;
import org.example.onlinestore.repos.ParameterRepo;
import org.example.onlinestore.services.interfaces.IParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class parameterService implements IParameterService {

    @Autowired
    private ParameterRepo parameterRepo;


    @Override
    public List<Parameter> findAllByProduct(Product product) {
        if(product == null)
            return null;

        return parameterRepo.findAllByProduct(product);
    }

    @Override
    public List<Parameter> findAllByAttribute(Attribute attribute) {
        if(attribute == null)
            return null;

        return parameterRepo.findAllByAttribute(attribute);
    }

    @Override
    public Parameter findByProductAndAttribute(Product product, Attribute attribute) {
        if(product == null || attribute == null)
            return null;

        Optional<Parameter> parameter = parameterRepo.findByProductAndAttribute(product, attribute);

        return parameter.orElse(null);
    }

    @Override
    public void save(Parameter parameter) {
        if(parameter != null)
            parameterRepo.save(parameter);
    }

    @Override
    public Parameter update(Product product, Attribute attribute, String value) {
        if(product == null || attribute == null)
            return null;

        Parameter parameter = parameterRepo.findByProductAndAttribute(product, attribute).orElse(null);

        if(parameter == null)
            return null;

        parameter.setValue(value);

        return parameterRepo.save(parameter);
    }

    @Override
    public Parameter deleteByProductAndAttribute(Product product, Attribute attribute) {
        if(product == null || attribute == null)
            return null;

        Parameter curParameter = parameterRepo.findByProductAndAttribute(product, attribute).orElse(null);
        if(curParameter != null)
            parameterRepo.delete(curParameter);

        return curParameter;
    }

    @Override
    public void deleteAll() {
        parameterRepo.deleteAll();
    }
}
