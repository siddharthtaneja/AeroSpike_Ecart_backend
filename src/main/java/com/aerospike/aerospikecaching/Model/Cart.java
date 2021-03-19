package com.aerospike.aerospikecaching.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    private int quantity;
    @ManyToOne
    private Items items;
    @ManyToOne
    private Users users;
}
