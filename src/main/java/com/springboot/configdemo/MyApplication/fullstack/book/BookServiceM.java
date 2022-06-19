package com.springboot.configdemo.MyApplication.fullstack.book;

import com.fasterxml.jackson.databind.JsonNode;
import com.springboot.configdemo.MyApplication.BookController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceM {

    @Autowired
    private BooksRepo booksRepo;

    Logger logger = LoggerFactory.getLogger(getClass());

    public JsonNode getBook(){
        logger.debug(String.format("Book Service - getList method call from %s", getMarkLogicBaseURL()));
        return booksRepo.getList();
    }
    public String getMarkLogicBaseURL(){
        logger.debug("Book Service - getMarkLogicBaseURL method call");
        return booksRepo.getMarkLogicBaseURL();
    }
}
