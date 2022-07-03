package com.springboot.configdemo.MyApplication.fullstack.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomersRepo customersRepo;

    Logger logger = LoggerFactory.getLogger(getClass());

    public List<Customer> getCustomerList() throws IOException {
        logger.debug("Customers Service - getCustomerList method call");
        return customersRepo.getCustomerList();
    }

    public List<Customer> customerFilter(String customerID) throws IOException {
        logger.debug(String.format("Customers Service - customerFilter method call for customerID : %s", customerID));
        return customersRepo.customerFilter(customerID);
    }

    public Customer addCustomer(Customer customer){
        logger.debug(String.format("Customers Service - addCustomer method call for customerID : %s", customer.getCustomerID()));
        return customersRepo.addCustomer(customer);
    }

    public ResponseEntity<HttpStatus> deleteCustomer(String customerID){
        logger.debug(String.format("Customers Service - deleteCustomer method call for customerID : %s", customerID));
        return customersRepo.deleteCustomer(customerID);
    }

    public String getMarkLogicBaseURL(){
        logger.debug("Customers Service - getMarkLogicBaseURL method call");
        return customersRepo.getMarkLogicBaseURL();
    }
}
