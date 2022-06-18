package com.springboot.configdemo.MyApplication.fullstack.customer;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceM {

    @Autowired
    private CustomerRepo customerRepo;

    public JsonNode getCustomers(){
        return customerRepo.getList();
    }
}
