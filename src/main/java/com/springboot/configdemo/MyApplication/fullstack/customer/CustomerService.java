package com.springboot.configdemo.MyApplication.fullstack.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomersRepo customersRepo;

    Logger logger = LoggerFactory.getLogger(getClass());

    public List<Customer> getCustomerList() {
        logger.debug("Customers Service - getCustomerList method call");
        return customersRepo.getCustomerList();
    }

    public List<Customer> customerFilter(String customerID){
        logger.debug(String.format("Customers Service - customerFilter method call for customerID : %s", customerID));
        return customersRepo.customerFilter(customerID);
    }
    public String getMarkLogicBaseURL(){
        logger.debug("Customers Service - getMarkLogicBaseURL method call");
        return customersRepo.getMarkLogicBaseURL();
    }
}
