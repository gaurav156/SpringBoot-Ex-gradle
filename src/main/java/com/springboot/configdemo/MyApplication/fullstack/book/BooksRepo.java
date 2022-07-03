package com.springboot.configdemo.MyApplication.fullstack.book;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BooksRepo {

    @Autowired
    public MarkLogicConfig markLogicConfig;
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

    public List<Book> bookFilter(String bookID) throws IOException {
        logger.debug(String.format("Books Repository - bookFilter method call for bookID : %s", bookID));
        return getBookList().stream().filter(p -> p.getBookID().equals(bookID)).collect(Collectors.toList());
    }

    public Book addBook(Book book){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.valueToTree(book);

        var manager = markLogicConfig.getDocumentManager(db);

        JacksonHandle handle = new JacksonHandle(node);
        manager.write("/db/book/"+book.getBookID()+".json", handle);

        logger.debug(String.format("Books Repository - addBook method call for bookID : %s", book.getBookID()));
        return book;
    }

    public ResponseEntity<HttpStatus> deleteBook(String bookID){
        logger.debug(String.format("Books Repository - deleteBook method call for bookID : %s", bookID));
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
