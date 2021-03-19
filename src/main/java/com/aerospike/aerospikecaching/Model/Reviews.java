package com.aerospike.aerospikecaching.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @ManyToOne
    private Items items;
    @ManyToOne
    private Users users;
    private String Review;
}
