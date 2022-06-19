package com.springboot.configdemo.MyApplication.fullstack.customer;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerControllerM {
    @Autowired
    private CustomerServiceM customerServiceM;

    @RequestMapping(method = RequestMethod.GET, value = "/marklogic/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public JsonNode getAllCustomers(){
        LoggerFactory.getLogger(getClass()).info(String.format("GET request for list of all Customers from %s", customerServiceM.getMarkLogicBaseURL()));
        return customerServiceM.getCustomers();
    }
}
