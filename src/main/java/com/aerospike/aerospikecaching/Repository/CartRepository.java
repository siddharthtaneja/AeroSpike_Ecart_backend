package com.aerospike.aerospikecaching.Repository;

import com.aerospike.aerospikecaching.Model.Cart;
import com.aerospike.aerospikecaching.Model.Items;
import com.aerospike.aerospikecaching.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface CartRepository extends JpaRepository<Cart,String> {
    List<Cart> findAllByUsers(Optional<Users> users);
    void deleteByUsersAndItems(Optional<Users> users, Items items);

}
