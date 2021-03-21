package com.aerospike.aerospikecaching.Service;

import com.aerospike.aerospikecaching.Model.Cart;
import com.aerospike.aerospikecaching.Model.Items;
import com.aerospike.aerospikecaching.Model.Orders;
import com.aerospike.aerospikecaching.Model.Users;
import com.aerospike.aerospikecaching.Repository.CartRepository;
import com.aerospike.aerospikecaching.Repository.ItemRepository;
import com.aerospike.aerospikecaching.Repository.OrderRepository;
import com.aerospike.aerospikecaching.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    UserRepository userRepositoryClass;
    @Autowired
    ItemRepository itemRepositoryClass;
    @Autowired
    OrderRepository ordersRepository;
    private List<Cart> getCartFromUser(Principal principal) {
        Users users = userRepositoryClass.findByEmail(principal.getName());
        List<Cart> cart = cartRepository.findAllByUsers(Optional.ofNullable(users));
        return cart;
    }


    public List<Cart> getEmail(Principal principal) {
        String email = principal.getName();
        Users users = userRepositoryClass.findByEmail(email);
        return cartRepository.findAllByUsers(Optional.ofNullable(users));
    }

    public String additemstoCart(Principal principal, Long id) {
        Items items = itemRepositoryClass.findByProductId(id);
        Users users = userRepositoryClass.findByEmail(principal.getName());
        List<Cart> cart = getCartFromUser(principal);
        for (int i = 0; i < cart.size(); i++) {
            Cart cartObj = cart.get(i);
            if (cartObj.getItems() == items) {
                cartObj.setQuantity(cartObj.getQuantity() + 1);
                cartRepository.save(cartObj);
                return "\"Quantity++\"";
            }
        }
        Cart cartObj = new Cart();
        cartObj.setQuantity(1);
        cartObj.setItems(items);
        cartObj.setUsers(users);
        cartRepository.save(cartObj);
        return "\"Item added\"";
    }

    @Transactional
    public String deleteItemFromCart(Long productid, Principal principal) {
        Items items = itemRepositoryClass.findByProductId(productid);
        Users users = userRepositoryClass.findByEmail(principal.getName());
        cartRepository.deleteByUsersAndItems(Optional.ofNullable(users), items);
        return "\"deletion completed\"";
    }

    public String increment(int value, Long productId, Principal principal) {
        List<Cart> cart = getCartFromUser(principal);
        Items items = itemRepositoryClass.findByProductId(productId);
        for (int i = 0; i < cart.size(); i++) {
            Cart cartObj = cart.get(i);
            if (cartObj.getItems() == items) {
                int x=cartObj.getQuantity() + value;
                if(x>=1) {
                    if(x > items.getQuantity()){
                        return "\"unsuccessfull\"";
                    }
                    else {
                        cartObj.setQuantity(x);
                        cartRepository.save(cartObj);
                        return "\"successfull\"";
                    }
                }
            }
        }
        return "\"unsuccessfull\"";
    }

    public String decrement(int value, Long productId, Principal principal) {
        List<Cart> cart = getCartFromUser(principal);
        Items items = itemRepositoryClass.findByProductId(productId);
        for (int i = 0; i < cart.size(); i++) {
            Cart cartObj = cart.get(i);

            if (cartObj.getItems() == items) {
                int x = cartObj.getQuantity() - value;
                if(x==1)
                {
                    cartObj.setQuantity(1);
                    cartRepository.save(cartObj);
                    return "\"successfull\"";
                }
                else if(x>1){
                    cartObj.setQuantity(x);
                    cartRepository.save(cartObj);
                    return "\"successfull\"";

                }
            }
        }
        return "\"unsuccessfull\"";
    }
    public List<Orders> checkOut(Principal principal) {
        Users users = userRepositoryClass.findByEmail(principal.getName());
        List<Cart> cartList = cartRepository.findAllByUsers(Optional.ofNullable(users));
        for(Cart cart : cartList) {
            Orders orders = new Orders();
            orders.setUserId(cart.getUsers().getId());
            orders.setQuantity(cart.getQuantity());
            orders.setPrice(cart.getItems().getPrice());
            Items items = itemRepositoryClass.findByProductId(cart.getItems().getProductId());
            items.setQuantity(items.getQuantity() - cart.getQuantity());
            System.out.println(items.toString());
            orders.setItemName(cart.getItems().getName());
            orders.setDate(new Date());
            cartRepository.delete(cart);
            ordersRepository.saveAndFlush(orders);
        }
        return ordersRepository.findAllByUserId(users.getId());
    }
}
