package com.aerospike.aerospikecaching.Model;

import lombok.Data;
import org.springframework.cache.annotation.EnableCaching;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemName;
    private Date date;
    private Long userId;
    private Double price;
    private int quantity;
    private Double Discountprice;

}
