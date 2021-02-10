package org.example.onlinestore.controllers;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Category;
import org.example.onlinestore.domain.entityes.Parameter;
import org.example.onlinestore.domain.entityes.Product;
import org.example.onlinestore.domain.models.CategoryModel;
import org.example.onlinestore.exceptions.BadRequestException;
import org.example.onlinestore.exceptions.NotFoundException;
import org.example.onlinestore.services.interfaces.IAttributeService;
import org.example.onlinestore.services.interfaces.ICategoryService;
import org.example.onlinestore.services.interfaces.IParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IAttributeService attributeService;

    @Autowired
    private IParameterService parameterService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Category getCategory(@RequestParam(value = "id") Long categoryId) {
        return  categoryService.findById(categoryId).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/attributes/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Set<Attribute> getCategoryAttributes(@RequestParam(value = "id") Long categoryId){
        Category curCategory = categoryService.findById(categoryId).orElseThrow(NotFoundException::new);

        return curCategory.getAttributes();
    }

    @RequestMapping(value = "/products/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Set<Product> getCategoryProducts(@RequestParam(value = "id") Long categoryId){
        Category curCategory = categoryService.findById(categoryId).orElseThrow(NotFoundException::new);

        return curCategory.getProducts();
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryModel requestCategory) {
        List<Category> categoriesByName = categoryService.findAllByName(requestCategory.getName());

        if(categoriesByName.isEmpty()){
            Category newCategory = new Category();
            newCategory.setName(requestCategory.getName());
            categoryService.save(newCategory);

            addAttrFromReq(newCategory, requestCategory);

            return new ResponseEntity<>(categoryService.save(newCategory), HttpStatus.OK);
        }else
            throw new BadRequestException("CategoryName already exist");
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Category> updateCategory(@RequestBody CategoryModel requestCategory) {
        Category curCategory = categoryService.findById(requestCategory.getId()).orElseThrow(NotFoundException::new);
        List<Category> allCategoriesByName = categoryService.findAllByName(requestCategory.getName());

        if(allCategoriesByName.isEmpty())
            curCategory.setName(requestCategory.getName());

        addAttrFromReq(curCategory, requestCategory);

        return new ResponseEntity<>(categoryService.update(curCategory), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteCategory(@RequestParam("id") Long categoryId) {
        Category category = categoryService.findById(categoryId).orElseThrow(NotFoundException::new);
        Set<Product> categoryProducts = category.getProducts();

        if(categoryProducts.isEmpty()) {
            category.removeAllAttributes();
            category.removeAllProducts();
            categoryService.save(category); //update tables category_attribute and category_product
            categoryService.deleteById(category.getId());

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            StringBuilder message = new StringBuilder("THIS CATEGORY CONTAINS PRODUCTS: ");

            for(Product product: categoryProducts){
                message.append("\"").append(product.getName()).append("\", ");
            }

            message.delete(message.length() - 2, message.length());

            return new ResponseEntity<>(message.toString(), HttpStatus.CONFLICT);
        }
    }

    private void addAttrFromReq(Category curCategory, CategoryModel requestCategory){
        for(Long attributeId: requestCategory.getAttributes()){
            Attribute attrById = attributeService.findById(attributeId).orElse(null);

            if(attrById!=null) {
                if (curCategory.addAttribute(attrById)) {

                    addParametersToProductsOfCategory(curCategory, attrById);
                }
            }
        }
        removeUnnecessaryAttributes(curCategory, requestCategory);
    }

    private void addParametersToProductsOfCategory(Category curCategory, Attribute newAttribute){
        for (Product product : curCategory.getProducts()) {

            Parameter newParameter = new Parameter();
            newParameter.setProduct(product);
            newParameter.setAttribute(newAttribute);
            newParameter.setValue("unknown");

            parameterService.save(newParameter);
        }
    }

    private void removeUnnecessaryAttributes(Category curCategory, CategoryModel updateCategory){
        List<Attribute> categoryAttributes = new ArrayList<>(curCategory.getAttributes());

        for(Attribute attribute: categoryAttributes){
            if(!updateCategory.getAttributes().contains(attribute.getId())){
                curCategory.removeAttribute(attribute);

                for (Product product : curCategory.getProducts()) {
                    parameterService.deleteByProductAndAttribute(product, attribute);
                }
            }
        }
    }
}


