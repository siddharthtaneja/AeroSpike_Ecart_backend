package com.aerospike.aerospikecaching.Repository;

import com.aerospike.aerospikecaching.Model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Items,Long> {
    Items findByProductId(Long productId);
}
