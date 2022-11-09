package com.springdatajpa.springboot.api;

import com.springdatajpa.springboot.entity.Cart;
import com.springdatajpa.springboot.entity.Product;
import com.springdatajpa.springboot.entity.User;
import com.springdatajpa.springboot.key.CartKey;
import com.springdatajpa.springboot.repository.CartRepository;
import com.springdatajpa.springboot.repository.ProductRepository;
import com.springdatajpa.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    @PostMapping
    public User addUser(@RequestBody User user){

        User savedObject = userRepository.save(user);
        return userRepository.findById(user.getId()).get();

    }

    @GetMapping
    public List<User> getUser()
    {
        return userRepository.findAll();
    }

    @PutMapping (value = "/wishlist/{uid}/{pid}")
    public Long addToWishlist(@PathVariable("uid") Long uid, @PathVariable("pid") Long pid){

        User user = userRepository.findById(uid).get();
        Product product = productRepository.findById(pid).get();
        user.getAddToWishlist().add(product);
        product.getUsersWishlisted().add(user);
        this.userRepository.save(user);
//        this.productRepository.save(product);
        return user.getId();
    }

    @GetMapping(value = "/wishlist/{uid}")
    public List<Product> wishList(@PathVariable("uid")Long uid){

        User user = userRepository.findById(uid).get();
        List<Product> list = user.getAddToWishlist();
        for (int i = 0; i< list.size();i++){
            Product product = list.get(i);
            product.setUsersWishlisted(null);
            product.setCartUser(null);
        }
        return list;
    }

    @PostMapping(value = "/cart")
    public String addToCart(@RequestBody Map<String, Long> cartDetails){

        Long uid = cartDetails.get("userId");
        Long pid = cartDetails.get("productId");


        User user = userRepository.findById(uid).get();
        Product product = productRepository.findById(pid).get();

        CartKey cartKey = new CartKey();
        cartKey.setProductId(pid);
        cartKey.setUserId(uid);

        Cart cart = new Cart();
        cart.setQty(Math.toIntExact(cartDetails.get("quantity")));
        cart.setProduct(product);
        cart.setUser(user);
        cart.setProduct(product);
        cart.setId(cartKey);

//        user.getAddToCart().add(cart);
//        product.getCartUser().add(cart);
        this.cartRepository.save(cart);

        return "Cart created Successfully";
    }

    @GetMapping(value = "/cart/{uid}")
    public List<Cart> addToCart(@PathVariable("uid") Long uid){

        List<Cart> carts = (userRepository.findById(uid).get()).getAddToCart();

        for (int i= 0; i < carts.size(); i++){
            Cart cart = carts.get(i);

            cart.getUser().setAddToCart(null);
            cart.getUser().setAddToWishlist(null);
            cart.getProduct().setCartUser(null);
            cart.getProduct().setUsersWishlisted(null);
        }
        return carts;
    }

//    @DeleteMapping(value = "/deletewish/{id}")
//    public void deleteProduct(@PathVariable ("id")Long id){
//
//        productRepository.deleteById(id);
//
//    }

}
