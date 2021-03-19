package com.aerospike.aerospikecaching.Repository;

import com.aerospike.aerospikecaching.Model.employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface employeeRepository extends JpaRepository<employee,Integer> {
    List<employee> findAll();
}
