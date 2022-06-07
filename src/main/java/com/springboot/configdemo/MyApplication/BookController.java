package com.springboot.configdemo.MyApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookManager bookManager;

    @RequestMapping(method = RequestMethod.GET, value = "/book", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Book> readAllBooks(){
        logger.info("List of Books Printed");
        return bookManager.getList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book/category/{category}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Book> readBook(@PathVariable("category") String category){
        logger.info("List of Books of category : "+ category+" Printed");
        return bookManager.categoryFilter(category);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book/author/{author}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Book> readBookByAuthor(@PathVariable("author") String author){
        logger.info("List of Books by Author : "+ author+" Printed");
        return bookManager.authorFilter(author);
    }

    @RequestMapping(value = "/book/xml", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public List<Book> readBookInXML(){
        logger.info("List of Books Printed in XML format");
        return bookManager.getList();
    }

    @RequestMapping(value = "/book/json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Book> readBookInJSON(){
        logger.info("List of Books Printed in JSON format");
        return bookManager.getList();
    }
}
