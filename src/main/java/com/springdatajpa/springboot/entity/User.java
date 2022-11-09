package com.springdatajpa.springboot.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String userName;
    String userEmail;
    String userAddress;
    String contactNo;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     @JoinTable(name = "wishlist_1", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> addToWishlist = new LinkedList<>();

    @OneToMany(mappedBy = "user")
    List<Cart> addToCart = new LinkedList<>();

    public List<Cart> getAddToCart() {
        return addToCart;
    }

    public void setAddToCart(List<Cart> addToCart) {
        this.addToCart = addToCart;
    }

    public List<Product> getAddToWishlist() {
        return addToWishlist;
    }

    public void setAddToWishlist(List<Product> addToWishlist) {
        this.addToWishlist = addToWishlist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
