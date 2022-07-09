package com.springboot.configdemo.MyApplication.fullstack.customer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;
import com.springboot.configdemo.MyApplication.MarkLogicConfig;

import com.springboot.configdemo.MyApplication.fullstack.book.BooksRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomersRepo {

    @Autowired
    public MarkLogicConfig markLogicConfig;

    @Autowired
    private BooksRepo booksRepo;

//    @Autowired
//    public CustomersRepo(@Lazy BooksRepo booksRepo) {
//        this.booksRepo = booksRepo;
//    }
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

    public Customer customerFilter(String customerID) throws IOException {
        logger.debug(String.format("Customers Repository - customerFilter method call for customerID : %s", customerID));
        Customer customer = new Customer();
        for(Customer i : getCustomerList()){
            if(i.getCustomerID().equals(customerID)){
                customer = i;
            }
        }
//        return getCustomerList().stream().filter(p -> p.getCustomerID().equals(customerID)).collect(Collectors.toList());
        return customer;
    }

    public Customer addCustomer(Customer customer) throws IOException {

        booksRepo.updateCustomerID(customer.getBookID(), customer.getCustomerID());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.valueToTree(customer);

        var manager = markLogicConfig.getDocumentManager(db);

        JacksonHandle handle = new JacksonHandle(node);
        manager.write("/db/customer/"+customer.getCustomerID()+".json", handle);

        logger.debug(String.format("Customers Repository - addCustomer method call for customerID : %s", customer.getCustomerID()));
        return customer;
    }

    public Customer putCustomer(Customer customer) throws IOException {
        Customer customer2 = customerFilter(customer.getCustomerID());
        LinkedHashSet<String> bookID1 = customer.getBookID();
        LinkedHashSet<String> bookID2 = customer2.getBookID();

        LinkedHashSet<String> bookToBeUpdated = bookID1.stream().filter(p -> !bookID2.contains(p)).collect(Collectors.toCollection(LinkedHashSet::new));
        LinkedHashSet<String> bookToBeDeleted = bookID2.stream().filter(p -> !bookID1.contains(p)).collect(Collectors.toCollection(LinkedHashSet::new));

        booksRepo.updateCustomerID(bookToBeUpdated, customer.getCustomerID());
        booksRepo.removeCustomerID(bookToBeDeleted, customer.getCustomerID());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.valueToTree(customer);

        var manager = markLogicConfig.getDocumentManager(db);

        JacksonHandle handle = new JacksonHandle(node);
        manager.write("/db/customer/"+customer.getCustomerID()+".json", handle);

        logger.debug(String.format("Customers Repository - putCustomer method call for customerID : %s", customer.getCustomerID()));
        return customer;
    }

    public void updateBookID(LinkedHashSet<String> customerID, String bookID) throws IOException {
        LinkedHashSet<String> book = null;
        for (String i : customerID) {
            book = customerFilter(i).getBookID();
            book.add(bookID);
        }
        for(String i : customerID){
            Customer customer = customerFilter(i);
            customer.setBookID(book);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.valueToTree(customer);

            var manager = markLogicConfig.getDocumentManager(db);

            JacksonHandle handle = new JacksonHandle(node);
            manager.write("/db/customer/"+customer.getCustomerID()+".json", handle);

            logger.debug(String.format("Customers Repository - updateBookID method call for bookID : %s", bookID));
        }
    }

    public void removeBookID(LinkedHashSet<String> customerID, String bookID) throws IOException {
        for (String i : customerID) {
            Customer customer = customerFilter(i);
            customer.deleteBookID(bookID);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.valueToTree(customer);

            var manager = markLogicConfig.getDocumentManager(db);

            JacksonHandle handle = new JacksonHandle(node);
            manager.write("/db/customer/"+customer.getCustomerID()+".json", handle);

            logger.debug(String.format("Customers Repository - removeBookID method call for bookID : %s", bookID));
        }
    }

    public ResponseEntity<HttpStatus> deleteCustomer(String customerID) throws IOException {
        logger.debug(String.format("Customers Repository - deleteCustomer method call for customerID : %s", customerID));

        Customer customer = customerFilter(customerID);
        LinkedHashSet<String> bookID = customer.getBookID();
        booksRepo.removeCustomerID(bookID, customerID);

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
