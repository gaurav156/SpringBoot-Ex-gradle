package com.springboot.configdemo.MyApplication.fullstack.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BooksRepo booksRepo;

    Logger logger = LoggerFactory.getLogger(getClass());

    public List<Book> getBookList() throws IOException {
        logger.debug("Books Service - getBookList method call");
        return booksRepo.getBookList();
    }

    public Book bookFilter(String bookID) throws IOException {
        logger.debug(String.format("Books Service - bookFilter method call for bookID : %s", bookID));
        return booksRepo.bookFilter(bookID);
    }

    public Book addBook(Book book) throws IOException {
        logger.debug(String.format("Books Service - addBook method call for bookID : %s", book.getBookID()));
        return booksRepo.addBook(book);
    }

    public ResponseEntity<HttpStatus> deleteBook(String bookID) throws IOException {
        logger.debug(String.format("Books Service - deleteBook method call for bookID : %s", bookID));
        return booksRepo.deleteBook(bookID);
    }

    public Book putBook(Book book) throws IOException {
        logger.debug(String.format("Books Service - putBook method call for bookID : %s", book.getBookID()));
        return booksRepo.putBook(book);
    }

    public String getMarkLogicBaseURL(){
        logger.debug("BookJ Service - getMarkLogicBaseURL method call");
        return booksRepo.getMarkLogicBaseURL();
    }

}
