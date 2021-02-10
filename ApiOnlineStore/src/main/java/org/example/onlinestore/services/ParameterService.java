package org.example.onlinestore.services;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Parameter;
import org.example.onlinestore.domain.entityes.Product;
import org.example.onlinestore.exceptions.BadRequestException;
import org.example.onlinestore.exceptions.NotFoundException;
import org.example.onlinestore.repos.ParameterRepo;
import org.example.onlinestore.services.interfaces.IParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParameterService implements IParameterService {

    @Autowired
    private ParameterRepo parameterRepo;

    @Override
    public List<Parameter> findAllByProduct(Product product) {
        if(product != null) {
            return parameterRepo.findAllByProduct(product);
        }else{
            throw new BadRequestException("Product is null");
        }
    }

    @Override
    public List<Parameter> findAllByAttribute(Attribute attribute) {
        if(attribute != null) {
            return parameterRepo.findAllByAttribute(attribute);
        }else{
            throw new BadRequestException("Attribute is null");
        }
    }

    @Override
    public Optional<Parameter> findByProductAndAttribute(Product product, Attribute attribute) {
        if(product == null || attribute == null)
            throw new BadRequestException("Product or attribute is null");

        return parameterRepo.findByProductAndAttribute(product, attribute);
    }

    @Override
    public void save(Parameter parameter) {
        if(parameter != null)
            parameterRepo.save(parameter);
    }

    @Override
    public Parameter update(Product product, Attribute attribute, String value) {
        if(product == null || attribute == null)
            throw new BadRequestException("Product or attribute is null");

        Parameter parameter = parameterRepo.findByProductAndAttribute(product, attribute).orElseThrow(
                () -> new BadRequestException("Parameter does not exist")
        );

        parameter.setValue(value);

        return parameterRepo.save(parameter);
    }

    @Override
    public Parameter deleteByProductAndAttribute(Product product, Attribute attribute) {
        if(product == null || attribute == null)
            throw new BadRequestException("Product or attribute is null");

        Parameter curParameter = parameterRepo.findByProductAndAttribute(product, attribute).orElseThrow(NotFoundException::new);

        parameterRepo.delete(curParameter);

        return curParameter;
    }

    @Override
    public void deleteAll() {
        parameterRepo.deleteAll();
    }

    @Override
    public void deleteAll(List<Parameter> parameters) {
        parameterRepo.deleteAll(parameters);
    }
}
