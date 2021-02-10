package org.example.onlinestore.services;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Parameter;
import org.example.onlinestore.exceptions.BadRequestException;
import org.example.onlinestore.exceptions.NotFoundException;
import org.example.onlinestore.repos.AttributeRepo;
import org.example.onlinestore.services.interfaces.IAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttributeService implements IAttributeService {
    @Autowired
    private AttributeRepo attributeRepo;

    @Override
    public List<Attribute> findAll() {
        return attributeRepo.findAll();
    }

    @Override
    public Optional<Attribute> findById(Long id) {
        if(id != null){
            return attributeRepo.findById(id);
        }else{
            throw new BadRequestException("AttributeId is null");
        }
    }

    @Override
    public Attribute save(Attribute attribute) {
        if(attribute != null) {
            return attributeRepo.save(attribute);
        }else {
            throw new BadRequestException("Attribute is null");
        }
    }

    @Override
    public Attribute update(Long id, String name) {
        if(id != null) {
            Attribute curAttr = attributeRepo.findById(id).orElseThrow(NotFoundException::new);

            curAttr.setName(name);

            return attributeRepo.save(curAttr);
        }else {
            throw new BadRequestException("AttributeId is null");
        }
    }

    @Override
    public Attribute deleteById(Long id) {
        if(id != null){
            Attribute curAttribute = attributeRepo.findById(id).orElse(null);

            if(curAttribute != null)
                attributeRepo.delete(curAttribute);

            return curAttribute;
        }else {
            throw new BadRequestException("AttributeId is null");
        }
    }

    @Override
    public void deleteAll() {
        attributeRepo.deleteAll();
    }

    @Override
    public List<Attribute> findAllByName(String attributeName) {
        if(attributeName != null) {
            return attributeRepo.findAllByName(attributeName);
        }else {
            throw new BadRequestException("AttributeName is null");
        }
    }
}
