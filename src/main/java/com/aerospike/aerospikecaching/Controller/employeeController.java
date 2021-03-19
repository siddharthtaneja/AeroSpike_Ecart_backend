package com.aerospike.aerospikecaching.Controller;

import com.aerospike.aerospikecaching.Model.employee;
import com.aerospike.aerospikecaching.Repository.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class employeeController {
    @Autowired
    employeeRepository employeeRepository;
    @PostMapping("/add")
    @Cacheable(value = "test")
    public Long AddEmployee(@RequestBody employee employee){
        employeeRepository.save(employee);
        return employee.getEmployeeId();
    }
    @GetMapping("/getAll")
    @Cacheable(value = "test")
    public List<employee> getAll() {
        return employeeRepository.findAll();
    }
}
