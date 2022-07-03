package com.springboot.configdemo.MyApplication.fullstack.customer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;
import com.springboot.configdemo.MyApplication.MarkLogicConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//    private List<Customer> customerList = new ArrayList<>();
    private final String db = "customers";
    Logger logger = LoggerFactory.getLogger(getClass());

//    @PostConstruct
//    public void dbConnect() throws IOException {
////        ObjectMapper mapper = new ObjectMapper();
////        String docId = "/db/customer.json";
////        this.customerList = mapper.readValue(markLogicConfig.getJsonParser(db , docId), new TypeReference<>() {
////        });
////        logger.debug(String.format("Customers Repository successfully connected to Database port : %s", getMarkLogicBaseURL()));
//
//        logger.debug(String.format("Customers Repository successfully connected to Database port : %s", getMarkLogicBaseURL()));
//
//        JSONDocumentManager docMgr = markLogicConfig.getJSONDocumentManager(db);
//        int i = 1;
//        while(docMgr.read("/db/customer/"+i+".json").hasContent()) {
//            ObjectMapper mapper = new ObjectMapper();
//            String docId = "/db/customer/"+i+".json";
//            this.customerList.add(mapper.readValue(markLogicConfig.getJsonParser(db, docId), new TypeReference<Customer>() {
//            }));
//            i++;
//        }
//    }

    public List<Customer> getCustomerList() throws IOException {
        List<Customer> customerList = new ArrayList<>();

        JSONDocumentManager docMgr = markLogicConfig.getJSONDocumentManager(db);
        int i = 1;
        while(docMgr.read("/db/customer/"+i+".json").hasContent()) {
            ObjectMapper mapper = new ObjectMapper();
            String docId = "/db/customer/"+i+".json";
            customerList.add(mapper.readValue(markLogicConfig.getJsonParser(db, docId), new TypeReference<Customer>() {
            }));
            i++;
        }

        logger.debug("Customers Repository - getCustomerList method call");
        return Collections.unmodifiableList(customerList);
    }

    public List<Customer> customerFilter(String customerID) throws IOException {
        logger.debug(String.format("Customers Repository - customerFilter method call for customerID : %s", customerID));
        return getCustomerList().stream().filter(p -> p.getCustomerID().equals(customerID)).collect(Collectors.toList());
    }

    public Customer addCustomer(Customer customer){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.valueToTree(customer);

        var manager = markLogicConfig.getDocumentManager(db);

        JacksonHandle handle = new JacksonHandle(node);
        manager.write("/db/customer/"+customer.getCustomerID()+".json", handle);

        logger.debug(String.format("Customers Repository - addCustomer method call for customerID : %s", customer.getCustomerID()));
        return customer;
    }

    public ResponseEntity<HttpStatus> deleteCustomer(String customerID){
        logger.debug(String.format("Customers Repository - deleteCustomer method call for customerID : %s", customerID));
        try {
            var manager = markLogicConfig.getDocumentManager(db);
            manager.delete("/db/customer/" + customerID + ".json");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
    }

    public String getMarkLogicBaseURL(){
        logger.debug("Customers Repository - getMarkLogicBaseURL method call");
        return markLogicConfig.getMarkLogicBaseURL(db);
    }
}
