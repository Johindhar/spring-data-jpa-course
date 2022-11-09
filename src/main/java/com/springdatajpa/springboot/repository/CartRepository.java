package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Cart;
import com.springdatajpa.springboot.key.CartKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, CartKey> {

}
