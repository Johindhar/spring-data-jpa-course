package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod() {
        //create product
        Product product = new Product();
        product.setName("Product1");
        product.setDescription("product 1 description");
        product.setSku(100);
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
    void updateUsingSaveMethod() {

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
    void findByIDMethod() {

        Long id = 1L;
        Product product = productRepository.findById(id).get();
    }

//    @Test
//    void saveAllMethod() {
//
//        Product product = new Product();
//        product.setName("Product5");
//        product.setDescription("product 5 description");
//        product.setSku("100D");
//        product.setPrice(new BigDecimal(700));
//        product.setActive(true);
//        product.setImageUrl("product5.png");
//
//        Product product3 = new Product();
//        product3.setName("Product9");
//        product3.setDescription("product 9 description");
//        product3.setSku("100ADE");
//        product3.setPrice(new BigDecimal(360));
//        product3.setActive(true);
//        product3.setImageUrl("product9.png");
//
//        productRepository.saveAll(List.of(product, product3));
//
//    }

    @Test
    void findAllMethod() {

        List<Product> product = productRepository.findAll();

        product.forEach((p) -> {
            System.out.println(p.getName());
        });

    }

    @Test
    void deleteByIdMethod() {

        Long id = 2L;
        productRepository.deleteById(id);
    }

    @Test
    void deleteMethod() {
        //find entity by id
        Long id = 3L;
        Product product = productRepository.findById(id).get();

        //delete (entity)
        productRepository.delete(product);

    }

    @Test
    void deleteAllMethod() {
//        productRepository.deleteAll();

        Product product = productRepository.findById(4L).get();
        Product product1 = productRepository.findById(5L).get();

        productRepository.deleteAll(List.of(product, product1));

    }

    @Test
    void existByIDMethod() {

        Long id = 2L;
        boolean result = productRepository.existsById(id);
        System.out.println(result);
    }

    @Test
    void countMethod() {
        long count = productRepository.count();
        System.out.println(count);
    }

//        @Test
//        void selectAll()
//        {
//        Collection<Product> list = productRepository.findAllProducts();
//        System.out.println(list);
//        }
//
//    @Test
//    void stockNotZero()
//    {
//        Collection<Product> list = productRepository.stockNotZero();
//        System.out.println(list);
//    }

}




