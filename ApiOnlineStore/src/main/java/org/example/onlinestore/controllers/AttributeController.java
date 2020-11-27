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

    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Attribute getAttribute(@RequestParam(value = "id") Attribute attribute) {return  attribute;}

}
