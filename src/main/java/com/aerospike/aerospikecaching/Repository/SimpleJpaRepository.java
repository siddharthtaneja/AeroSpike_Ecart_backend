package com.aerospike.aerospikecaching.Repository;

import com.aerospike.aerospikecaching.Model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimpleJpaRepository extends JpaRepository<Items,Long> {
    @Override
    List<Items> findAll();
    List<Items> findByCategory(String category);
    List<Items> findByCategoryAndPriceBetween(String category,double min,double max);
    List<Items> findByPriceBetween(double min,double max);
    List<Items> findByName(String name);
}
