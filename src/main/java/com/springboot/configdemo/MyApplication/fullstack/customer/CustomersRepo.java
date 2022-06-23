package com.springboot.configdemo.MyApplication.fullstack.customer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.configdemo.MyApplication.MarkLogicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomersRepo {

    @Autowired
    public MarkLogicConfig markLogicConfig;
    private List<Customer> customerList = new ArrayList<>();
    private final String db = "customers";
    Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void dbConnect() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String docId = "/db/customer.json";
        this.customerList = mapper.readValue(markLogicConfig.getJsonParser(db , docId), new TypeReference<>() {
        });
        logger.debug(String.format("Customers Repository successfully connected to Database port : %s", getMarkLogicBaseURL()));
    }

    public List<Customer> getCustomerList() {
        logger.debug("Customers Repository - getCustomerList method call");
        return Collections.unmodifiableList(customerList);
    }

    public List<Customer> customerFilter(String customerID){
        logger.debug(String.format("Customers Repository - customerFilter method call for customerID : %s", customerID));
        return customerList.stream().filter(p -> p.getCustomerID().equals(customerID)).collect(Collectors.toList());
    }

    public String getMarkLogicBaseURL(){
        logger.debug("Customers Repository - getMarkLogicBaseURL method call");
        return markLogicConfig.getMarkLogicBaseURL(db);
    }
}
