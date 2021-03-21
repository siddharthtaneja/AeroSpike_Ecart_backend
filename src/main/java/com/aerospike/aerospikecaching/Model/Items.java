package com.aerospike.aerospikecaching.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long productId;
    private String name;
    private double price;
    private String image;
    private String category;
    private int quantity;


}
