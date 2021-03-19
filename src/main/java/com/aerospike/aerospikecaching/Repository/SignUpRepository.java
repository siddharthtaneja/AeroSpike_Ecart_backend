package com.aerospike.aerospikecaching.Repository;

import com.aerospike.aerospikecaching.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignUpRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String name);
}
