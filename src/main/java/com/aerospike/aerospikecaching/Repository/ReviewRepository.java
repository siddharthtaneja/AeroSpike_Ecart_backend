package com.aerospike.aerospikecaching.Repository;

import com.aerospike.aerospikecaching.Model.Items;
import com.aerospike.aerospikecaching.Model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Reviews,Long> {
    List<Reviews> findByItems(Items items);
    Reviews findByProductId(Long A);

}
