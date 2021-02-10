package org.example.onlinestore.controllers;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Category;
import org.example.onlinestore.domain.entityes.Parameter;
import org.example.onlinestore.domain.entityes.Product;
import org.example.onlinestore.domain.models.ProductModel;
import org.example.onlinestore.exceptions.BadRequestException;
import org.example.onlinestore.exceptions.NotFoundException;
import org.example.onlinestore.services.interfaces.ICategoryService;
import org.example.onlinestore.services.interfaces.IParameterService;
import org.example.onlinestore.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IProductService productService;

    @Autowired
    IParameterService parameterService;

    @Autowired
    ICategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Product getProduct(@RequestParam(value = "id") Long productId) {
        return  productService.findById(productId).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/parameters/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Parameter> getProductParameters(@RequestParam(value = "id") Long productId) {
        Product product = productService.findById(productId).orElseThrow(NotFoundException::new);

        return  parameterService.findAllByProduct(product);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Product> createProduct(@RequestBody ProductModel requestProduct){
        Category productCategory = categoryService.findById(requestProduct.getCategoryId()).orElseThrow(
                () -> new BadRequestException("ProductCategory does not exist")
        );
        String productName = requestProduct.getName();

        if(!productService.findAllByName(productName).isEmpty())
            throw new BadRequestException("ProductName already exist");

        Product newProduct = new Product();
        newProduct.setName(productName);
        newProduct.setPrice(requestProduct.getPrice());
        newProduct.setCount(requestProduct.getCount());
        productService.save(newProduct);
        newProduct.setCategory(productCategory);

        productService.save(newProduct);

        setParametersToProduct(newProduct, requestProduct);

        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductModel requestProduct){
        Product curProduct = productService.findById(requestProduct.getId()).orElseThrow(NotFoundException::new);
        categoryService.findById(requestProduct.getCategoryId()).ifPresent(curProduct::setCategory);

        curProduct.setName(requestProduct.getName());
        curProduct.setPrice(requestProduct.getPrice());
        curProduct.setCount(requestProduct.getCount());
        productService.save(curProduct);

        setParametersToProduct(curProduct, requestProduct);

        return new ResponseEntity<>(curProduct, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Product> deleteProduct(@RequestParam("id") Long productId){
        Product product = productService.findById(productId).orElseThrow(NotFoundException::new);

        List<Parameter> productsParameters = parameterService.findAllByProduct(product);
        parameterService.deleteAll(productsParameters);

        Category productCategory = product.getCategory();
        productCategory.removeProduct(product);
        categoryService.save(productCategory);

        productService.deleteById(productId);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    private void setParametersToProduct(Product product, ProductModel requestProduct){
        for(Attribute attribute: product.getCategory().getAttributes()){
            Map<Long, String> requestParams = requestProduct.getParameters();

            Parameter newParameter = parameterService.findByProductAndAttribute(product, attribute).orElse(null);

            if(newParameter == null) {
                newParameter = new Parameter();
                newParameter.setProduct(product);
                newParameter.setAttribute(attribute);
                newParameter.setValue(requestParams.getOrDefault(attribute.getId(), "unknown"));
            }else{
                if(requestParams.containsKey(attribute.getId())){
                    newParameter.setValue(requestParams.get(attribute.getId()));
                }
            }
            parameterService.save(newParameter);
        }
    }


}
