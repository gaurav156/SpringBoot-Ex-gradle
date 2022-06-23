package com.springboot.configdemo.MyApplication.fullstack.book;

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
public class BooksRepo {

    @Autowired
    public MarkLogicConfig markLogicConfig;
    private List<Book> bookList = new ArrayList<>();
    private final String db = "books";
    Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void dbConnect() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String docId = "/db/book.json";
        this.bookList = mapper.readValue(markLogicConfig.getJsonParser(db , docId), new TypeReference<>() {
        });
        logger.debug(String.format("Books Repository successfully connected to Database port : %s", getMarkLogicBaseURL()));
    }

    public List<Book> getBookList(){
        logger.debug("Books Repository - getBookList method call");
        return Collections.unmodifiableList(bookList);
    }

    public List<Book> bookFilter(String bookID){
        logger.debug(String.format("Books Repository - bookFilter method call for bookID : %s", bookID));
        return bookList.stream().filter(p -> p.getBookID().equals(bookID)).collect(Collectors.toList());
    }

    public String getMarkLogicBaseURL(){
        logger.debug("BookJ Repository - getMarkLogicBaseURL method call");
        return markLogicConfig.getMarkLogicBaseURL(db);
    }

}
