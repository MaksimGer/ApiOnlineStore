package org.example.onlinestore.controllers;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.services.interfaces.IAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attributes")
public class AttributeController {

    @Autowired
    private IAttributeService attributeService;

    @RequestMapping(value = "",
    method = RequestMethod.GET,
    produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Attribute> getAllAttributes(){
        return attributeService.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Attribute getAttribute(@RequestParam(value = "id") Attribute attribute) {return  attribute;}

    @RequestMapping(value = "", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Attribute createAttribute(@RequestBody String attributeName) {
        List<Attribute> attrsByName = attributeService.findAllByName(attributeName);

        if(attrsByName.isEmpty()){
            Attribute attribute = new Attribute();
            attribute.setName(attributeName);
            return attributeService.save(attribute);
        }else
            return null;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Attribute updateAttribute(@RequestBody Attribute attribute) {
        return attributeService.update(attribute.getId(), attribute.getName());
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public void deleteAttribute(@RequestParam("id") Attribute attribute) {
        attribute.removeAllCategories();
        attributeService.save(attribute);
        attributeService.deleteById(attribute.getId());
    }
}
