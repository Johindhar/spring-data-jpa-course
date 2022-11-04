package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByActiveTrue();

    List<Product> findByPriceBetween(BigDecimal one, BigDecimal two);

    List<Product> findByPriceBetweenAndActiveTrue(BigDecimal one, BigDecimal two);

    List<Product> findBySkuNotOrderByIdDesc(Integer one);

    List<Product> findBySkuNotAndActiveTrue(Integer one);


}
