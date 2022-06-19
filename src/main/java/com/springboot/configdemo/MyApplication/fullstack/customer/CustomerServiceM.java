package com.springboot.configdemo.MyApplication.fullstack.customer;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceM {

    @Autowired
    private CustomerRepo customerRepo;

    Logger logger = LoggerFactory.getLogger(getClass());

    public JsonNode getCustomers(){
        logger.debug(String.format("Customers Service - getList method call from %s", getMarkLogicBaseURL()));
        return customerRepo.getList();
    }

    public String getMarkLogicBaseURL(){
        logger.debug("Customers Service - getMarkLogicBaseURL method call");
        return customerRepo.getMarkLogicBaseURL();
    }
}
