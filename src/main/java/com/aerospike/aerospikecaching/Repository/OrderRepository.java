package com.aerospike.aerospikecaching.Repository;

import com.aerospike.aerospikecaching.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,String> {
    List<Orders> findAllByUserId(Long id);
}
