package com.aerospike.aerospikecaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AerospikecachingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AerospikecachingApplication.class, args);
    }

}
