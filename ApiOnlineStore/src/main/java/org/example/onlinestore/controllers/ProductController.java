package org.example.onlinestore.controllers;

import org.example.onlinestore.domain.entityes.Attribute;
import org.example.onlinestore.domain.entityes.Category;
import org.example.onlinestore.domain.entityes.Parameter;
import org.example.onlinestore.domain.entityes.Product;
import org.example.onlinestore.domain.models.ProductModel;
import org.example.onlinestore.services.interfaces.ICategoryService;
import org.example.onlinestore.services.interfaces.IParameterService;
import org.example.onlinestore.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Product getProducts(@RequestParam(value = "id") Product product) { return  product; }

    @RequestMapping(value = "/parameters/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Parameter> getProductParameters(@RequestParam(value = "id") Product product) {
        if(product == null)
            return null;

        return  parameterService.findAllByProduct(product);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public Product createProduct(@RequestBody ProductModel requestProduct){
        Category productCategory = categoryService.findById(requestProduct.getCategoryId());
        String productName = requestProduct.getName();

        if(productCategory == null)
            return null;

        Product newProduct = new Product();
        newProduct.setName(productName);
        productService.save(newProduct);
        newProduct.setCategory(productCategory);

        productService.save(newProduct);

        setParametersToProduct(newProduct, requestProduct);

        return newProduct;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public Product updateProduct(@RequestBody ProductModel requestProduct){
        Product curProduct = productService.findById(requestProduct.getId());
        Category productCategory = categoryService.findById(requestProduct.getCategoryId());

        if(curProduct == null )
            return null;

        if(productCategory!= null)
            curProduct.setCategory(productCategory);

        curProduct.setName(requestProduct.getName());
        productService.save(curProduct);

        setParametersToProduct(curProduct, requestProduct);

        return curProduct;
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public Product deleteProduct(@RequestParam("id") Product product){
        if(product == null)
            return null;

        List<Parameter> productsParameters = parameterService.findAllByProduct(product);
        parameterService.deleteAll(productsParameters);
        if(product.getCategory() != null){
            product.getCategory().removeProduct(product);
            categoryService.save(product.getCategory());
        }
        productService.deleteById(product.getId());

        return product;
    }

    private void setParametersToProduct(Product product, ProductModel requestProduct){
        for(Attribute attribute: product.getCategory().getAttributes()){
            Map<Long, String> requestParams = requestProduct.getParameters();

            Parameter newParameter = parameterService.findByProductAndAttribute(product, attribute);

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
