package com.aerospike.aerospikecaching.Controller;

import com.aerospike.aerospikecaching.Model.Cart;
import com.aerospike.aerospikecaching.Model.Orders;
import com.aerospike.aerospikecaching.Model.Users;
import com.aerospike.aerospikecaching.Repository.OrderRepository;
import com.aerospike.aerospikecaching.Repository.UserRepository;
import com.aerospike.aerospikecaching.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mycart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository ordersRepository;
    @GetMapping("/cart")
    public List<Cart> getCart(Principal principal){
        return cartService.getEmail(principal);
    }
    @CacheEvict(value = "test",key = "#productId")
    @GetMapping("/cart/addItem/productId/{id}")
    public String addItemToCart(@PathVariable("id") Long productId, Principal principal){
        return cartService.additemstoCart(principal,productId);
    }
    @GetMapping("/cart/deleteItem/productId/{id}")
    public String deleteItemFromCart(@PathVariable("id") Long id,Principal principal){
        return cartService.deleteItemFromCart(id,principal);
    }
    @GetMapping("/cart/increment/{value}/product/{productId}")
    public String increment(@PathVariable("value") int value, @PathVariable("productId") Long productId,Principal principal){
        return cartService.increment(value,productId,principal);
    }
    @GetMapping("/cart/decrement/{value}/product/{productId}")
    public String decrement(@PathVariable("value") int value,@PathVariable("productId") Long productId,Principal principal){
        return cartService.decrement(value,productId,principal);
    }
    @GetMapping("checkout")
    @CacheEvict(value = "test",key = "56")
    public List<Orders> checkout(Principal principal) {
        return cartService.checkOut(principal);
    }
    @GetMapping("listcheckout")
    public List<Orders> list(Principal principal){
        Users users = userRepository.findByEmail(principal.getName());
        return ordersRepository.findAllByUserId(users.getId());
    }
}
