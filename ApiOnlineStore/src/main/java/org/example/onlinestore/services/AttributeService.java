package org.example.onlinestore.services;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Parameter;
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
    public Attribute findById(Long id) {
        if(id == null)
            return null;

        Optional<Attribute> attrById = attributeRepo.findById(id);

        return attrById.orElse(null);
    }

    @Override
    public Attribute save(Attribute attribute) {
        if(attribute != null)
            return attributeRepo.save(attribute);

        return null;
    }

    @Override
    public Attribute update(Long id, String name) {
        if(id == null)
            return null;

        Attribute curAttr = attributeRepo.findById(id).orElse(null);

        if(curAttr == null)
            return null;

        curAttr.setName(name);

        return attributeRepo.save(curAttr);
    }

    @Override
    public Attribute deleteById(Long id) {
        if(id == null)
            return null;

        Attribute curAttribute = attributeRepo.findById(id).orElse(null);

        if(curAttribute != null)
            attributeRepo.delete(curAttribute);

        return curAttribute;
    }

    @Override
    public void deleteAll() {
        attributeRepo.deleteAll();
    }

    @Override
    public List<Attribute> findAllByName(String attributeName) {
        if(attributeName == null)
            return null;

        return attributeRepo.findAllByName(attributeName);
    }
}
