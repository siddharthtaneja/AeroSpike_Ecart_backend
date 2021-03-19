package com.aerospike.aerospikecaching.config;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.aerospike.cache.AerospikeCacheManager;
import org.springframework.data.aerospike.convert.AerospikeTypeAliasAccessor;
import org.springframework.data.aerospike.convert.CustomConversions;
import org.springframework.data.aerospike.convert.MappingAerospikeConverter;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.data.aerospike.mapping.AerospikeMappingContext;
import org.springframework.data.mapping.model.SimpleTypeHolder;

import java.util.Arrays;

@Configuration
@Import(value = {MappingAerospikeConverter.class, AerospikeMappingContext.class, AerospikeTypeAliasAccessor.class, CustomConversions.class, SimpleTypeHolder.class})
public class AerospikeConfig {
    @Autowired
    private MappingAerospikeConverter mapp;
    @Bean(destroyMethod = "close")
    public AerospikeClient clientPolicy() {
        ClientPolicy clientPolicy = new ClientPolicy();
        clientPolicy.failIfNotConnected = true;
        return new AerospikeClient(clientPolicy,"172.28.128.3",3000);
    }
    @Bean
    public AerospikeCacheManager cacheManager(AerospikeClient ap){
        return new AerospikeCacheManager(ap,mapp);
    }
}
