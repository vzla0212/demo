package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/cust")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/save")
    public String calc(@RequestBody Customer customer) {


        customerRepository.save(customer);
        return "";
    }
}
