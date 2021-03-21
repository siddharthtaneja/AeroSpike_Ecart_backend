package com.aerospike.aerospikecaching.Controller;

import com.aerospike.aerospikecaching.Model.Users;
import com.aerospike.aerospikecaching.Repository.SignUpRepository;
import com.aerospike.aerospikecaching.Service.SignupService;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/sign")
@CrossOrigin(origins = "http://localhost:4200")
public class SignController {
    @Autowired
    SignUpRepository signupRepository;
    @Autowired
    SignupService signupservice;
    @GetMapping("/get")
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @Cacheable(value = "test",key = "176")
    public Users getData(Principal principal) {
        System.out.println("I am here bro");
        return signupservice.getPro(principal);
    }
}
