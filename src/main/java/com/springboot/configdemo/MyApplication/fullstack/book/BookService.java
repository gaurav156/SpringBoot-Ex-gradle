package com.springboot.configdemo.MyApplication.fullstack.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BooksRepo booksRepo;

    Logger logger = LoggerFactory.getLogger(getClass());

    public List<Book> getBookList() {
        logger.debug("Books Service - getBookList method call");
        return booksRepo.getBookList();
    }

    public List<Book> bookFilter(String bookID){
        logger.debug(String.format("Books Service - bookFilter method call for bookID : %s", bookID));
        return booksRepo.bookFilter(bookID);
    }

    public String getMarkLogicBaseURL(){
        logger.debug("BookJ Service - getMarkLogicBaseURL method call");
        return booksRepo.getMarkLogicBaseURL();
    }
}
