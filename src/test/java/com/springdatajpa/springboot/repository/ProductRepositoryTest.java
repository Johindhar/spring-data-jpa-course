package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod(){
        //create product
        Product product = new Product();
        product.setName("Product1");
        product.setDescription("product 1 description");
        product.setSku("100ABC");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageUrl("product1.png");

        //save product
        Product savedObject = productRepository.save(product);

        //display product
        System.out.println(savedObject.getId());
        System.out.println(savedObject.toString());

    }

    @Test
    void updateUsingSaveMethod(){

        //find or retrieve data using id
        Long id = 1L;

        Product product = productRepository.findById(id).get();

        //update entity information
        product.setName("Updated product 1");
        product.setDescription("Updated product 1 description");

        //save the info in DB
        productRepository.save(product);



    }

    @Test
    void findByIDMethod(){

        Long id = 1L;
        Product product = productRepository.findById(id).get();
    }

    @Test
    void saveAllMethod(){

        Product product = new Product();
        product.setName("Product2");
        product.setDescription("product 2 description");
        product.setSku("100ABCD");
        product.setPrice(new BigDecimal(200));
        product.setActive(true);
        product.setImageUrl("product2.png");

        Product product3 = new Product();
        product3.setName("Product3");
        product3.setDescription("product 3 description");
        product3.setSku("100ABCDE");
        product3.setPrice(new BigDecimal(300));
        product3.setActive(true);
        product3.setImageUrl("product3.png");

        productRepository.saveAll(List.of(product, product3));

    }

    @Test
    void findAllMethod(){

        List<Product> product = productRepository.findAll();

        product.forEach((p)->{
            System.out.println(p.getName());
        });

    }

}