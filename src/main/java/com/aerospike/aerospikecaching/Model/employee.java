package com.aerospike.aerospikecaching.Model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String position;

}
