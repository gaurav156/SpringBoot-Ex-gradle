package com.springboot.configdemo.MyApplication.fullstack.book;

import com.fasterxml.jackson.databind.JsonNode;
import com.springboot.configdemo.MyApplication.fullstack.book.BookServiceM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class bookControllerM {

    @Autowired
    private BookServiceM bookServiceM;

    @RequestMapping(method = RequestMethod.GET, value = "/marklogic/books", produces = {MediaType.APPLICATION_JSON_VALUE})
    public JsonNode readAllBooks(){
        return bookServiceM.getBook();
    }
}


