package com.springboot.configdemo.MyApplication.fullstack.book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;
import com.springboot.configdemo.MyApplication.MarkLogicConfig;
import com.springboot.configdemo.MyApplication.fullstack.customer.CustomersRepo;
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
public class BooksRepo {

    @Autowired
    public MarkLogicConfig markLogicConfig;

    @Autowired
    private CustomersRepo customersRepo;

//    @Autowired
//    public BooksRepo(@Lazy CustomersRepo customersRepo) {
//        this.customersRepo = customersRepo;
//    }
//    private List<Book> bookList = new ArrayList<>();
    private final String db = "books";
    Logger logger = LoggerFactory.getLogger(getClass());

//    @PostConstruct
//    public void dbConnect() throws IOException {
////        ObjectMapper mapper = new ObjectMapper();
////        String docId = "/db/book.json";
////        this.bookList = mapper.readValue(markLogicConfig.getJsonParser(db , docId), new TypeReference<>() {
////        });
////        logger.debug(String.format("Books Repository successfully connected to Database port : %s", getMarkLogicBaseURL()));
//        logger.debug(String.format("Books Repository successfully connected to Database port : %s", getMarkLogicBaseURL()));
//        JSONDocumentManager docMgr = markLogicConfig.getJSONDocumentManager(db);
//        int i = 1;
//        while(docMgr.read("/db/book/"+i+".json").hasContent()) {
//            ObjectMapper mapper = new ObjectMapper();
//            String docId = "/db/book/"+i+".json";
//            this.bookList.add(mapper.readValue(markLogicConfig.getJsonParser(db, docId), new TypeReference<Book>() {
//            }));
//            i++;
//        }
//    }

    public List<Book> getBookList() throws IOException {
        List<Book> bookList = new ArrayList<>();
        JSONDocumentManager docMgr = markLogicConfig.getJSONDocumentManager(db);
        int i = 1;
        while(docMgr.read("/db/book/"+i+".json").hasContent()) {
            ObjectMapper mapper = new ObjectMapper();
            String docId = "/db/book/"+i+".json";
            bookList.add(mapper.readValue(markLogicConfig.getJsonParser(db, docId), new TypeReference<Book>() {
            }));
            i++;
        }

        logger.debug("Books Repository - getBookList method call");
        return Collections.unmodifiableList(bookList);
    }

    public Book bookFilter(String bookID) throws IOException {
        logger.debug(String.format("Books Repository - bookFilter method call for bookID : %s", bookID));
//        return getBookList().stream().filter(p -> p.getBookID().equals(bookID)).collect(Collectors.toList());
        Book book = new Book();
        for(Book i : getBookList()){
            if(i.getBookID().equals(bookID)){
                book = i;
            }
        }
        return book;
    }

    public Book addBook(Book book) throws IOException {

        customersRepo.updateBookID(book.getCustomerID(), book.getBookID());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.valueToTree(book);

        var manager = markLogicConfig.getDocumentManager(db);

        JacksonHandle handle = new JacksonHandle(node);
        manager.write("/db/book/"+book.getBookID()+".json", handle);

        logger.debug(String.format("Books Repository - addBook method call for bookID : %s", book.getBookID()));
        return book;
    }

    public Book putBook(Book book) throws IOException {
        Book book2 = bookFilter(book.getBookID());
        LinkedHashSet<String> customerID1 = book.getCustomerID();
        LinkedHashSet<String> customerID2 = book2.getCustomerID();

        LinkedHashSet<String> customerToBeUpdated = customerID1.stream().filter(p -> !customerID2.contains(p)).collect(Collectors.toCollection(LinkedHashSet::new));
        LinkedHashSet<String> customerToBeDeleted = customerID2.stream().filter(p -> !customerID1.contains(p)).collect(Collectors.toCollection(LinkedHashSet::new));

        customersRepo.updateBookID(customerToBeUpdated, book.getBookID());
        customersRepo.removeBookID(customerToBeDeleted, book.getBookID());


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.valueToTree(book);

        var manager = markLogicConfig.getDocumentManager(db);

        JacksonHandle handle = new JacksonHandle(node);
        manager.write("/db/book/"+book.getBookID()+".json", handle);

        logger.debug(String.format("Books Repository - putBook method call for bookID : %s", book.getBookID()));
        return book;
    }


    public void updateCustomerID(LinkedHashSet<String> bookID, String customerID) throws IOException {
        LinkedHashSet<String> customer = null;
        for (String i : bookID) {
            customer = bookFilter(i).getCustomerID();
            customer.add(customerID);
        }
        for(String i : bookID){
            Book book = bookFilter(i);
            book.setCustomerID(customer);


            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.valueToTree(book);

            var manager = markLogicConfig.getDocumentManager(db);

            JacksonHandle handle = new JacksonHandle(node);
            manager.write("/db/book/"+book.getBookID()+".json", handle);

            logger.debug(String.format("Books Repository - updateCustomerID method call for customerID : %s", customerID));
        }
    }

    public void removeCustomerID(LinkedHashSet<String> bookID, String customerID) throws IOException {
        for (String i : bookID) {
            Book book = bookFilter(i);
            book.deleteCustomerID(customerID);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.valueToTree(book);

            var manager = markLogicConfig.getDocumentManager(db);

            JacksonHandle handle = new JacksonHandle(node);
            manager.write("/db/book/"+book.getBookID()+".json", handle);

            logger.debug(String.format("Books Repository - removeCustomerID method call for customerID : %s", customerID));
        }
    }

    public ResponseEntity<HttpStatus> deleteBook(String bookID) throws IOException {
        logger.debug(String.format("Books Repository - deleteBook method call for bookID : %s", bookID));

        Book book = bookFilter(bookID);
        LinkedHashSet<String> customerID = book.getCustomerID();
        customersRepo.removeBookID(customerID, bookID);

        try {
            var manager = markLogicConfig.getDocumentManager(db);
            manager.delete("/db/book/" + bookID + ".json");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
    }

    public String getMarkLogicBaseURL(){
        logger.debug("BookJ Repository - getMarkLogicBaseURL method call");
        return markLogicConfig.getMarkLogicBaseURL(db);
    }

}
