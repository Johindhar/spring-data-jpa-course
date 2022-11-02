package com.springdatajpa.springboot.api;


import com.springdatajpa.springboot.entity.Product;
import com.springdatajpa.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Product addProduct(@RequestBody Product product){

        Product savedObject = productRepository.save(product);
        return productRepository.findById(product.getId()).get();

    }

    @GetMapping
    public List<Product> getProducts()
    {
        return productRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable("id") Long id){

        return productRepository.findById(id).get();
    }

    @PutMapping(value = "/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("id")Long id){

        Product temp = productRepository.findById(id).get();
        temp.setName(product.getName());
        temp.setDescription(product.getDescription());
        temp.setSku(product.getSku());
        temp.setPrice(product.getPrice());
        temp.setActive(product.getActive());
        temp.setImageUrl(product.getImageUrl());

        productRepository.save(temp);

        return temp;

    }

    @DeleteMapping
    public void deleteProducts(){

        productRepository.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable ("id")Long id){

        productRepository.deleteById(id);

    }



}
