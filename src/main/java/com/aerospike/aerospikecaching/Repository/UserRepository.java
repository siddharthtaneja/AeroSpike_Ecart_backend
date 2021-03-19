package com.aerospike.aerospikecaching.Repository;

import com.aerospike.aerospikecaching.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,String> {
    @Override
    List<Users> findAll();

    Users findByEmail(String email);

    Optional<Users> findById(Long id);
}
