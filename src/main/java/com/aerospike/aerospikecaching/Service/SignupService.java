package com.aerospike.aerospikecaching.Service;

import com.aerospike.aerospikecaching.Model.Users;
import com.aerospike.aerospikecaching.Repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;
@Service
public class SignupService<SignupRepository> {
    @Autowired
    SignUpRepository signupRepository;
    public Users getPro(Principal principal) {
        Optional<Users> op=signupRepository.findByEmail(principal.getName());
        return op.get();
    }
}
