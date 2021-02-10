package org.example.onlinestore.controllers;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Category;
import org.example.onlinestore.exceptions.BadRequestException;
import org.example.onlinestore.exceptions.NotFoundException;
import org.example.onlinestore.services.interfaces.IAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/attributes")
@PreAuthorize("hasAuthority('ADMIN')")
public class AttributeController {

    @Autowired
    private IAttributeService attributeService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Attribute> getAllAttributes(){
        return attributeService.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Attribute getAttribute(@RequestParam(value = "id") Long id) {
        return attributeService.findById(id).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Attribute> createAttribute(@RequestBody String attributeName) {
        if(attributeName == null){
            throw new BadRequestException("AttributeName is null");
        }

        List<Attribute> attrsByName = attributeService.findAllByName(attributeName);

        if(attrsByName.isEmpty()){
            Attribute attribute = new Attribute();
            attribute.setName(attributeName);

            return new ResponseEntity<>(attributeService.save(attribute), HttpStatus.OK);
        }else
            throw new BadRequestException("AttributeName already exist");
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Attribute> updateAttribute(@RequestBody Attribute attribute) {
        if(attribute.getId() != null && attribute.getName() != null) {
            Attribute updatedAttribute = attributeService.update(attribute.getId(), attribute.getName());

            return new ResponseEntity<>(updatedAttribute, HttpStatus.OK);
        }else
            throw new BadRequestException("AttributeId or attributeName is null");
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<String> deleteAttribute(@RequestParam("id") Attribute attribute) {
        Set<Category> attributeCategories = attribute.getCategories();

        if(attributeCategories.isEmpty()){
            attributeService.deleteById(attribute.getId());

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            StringBuilder message = new StringBuilder("THIS ATTRIBUTE IS CONTAINED IN CATEGORIES: ");

            for(Category category: attributeCategories){
                message.append("\"").append(category.getName()).append("\", ");
            }

            message.delete(message.length() - 2, message.length());

            return new ResponseEntity<>(message.toString(), HttpStatus.CONFLICT);
        }
    }
}
