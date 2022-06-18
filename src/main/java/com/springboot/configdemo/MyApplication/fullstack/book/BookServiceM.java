package com.springboot.configdemo.MyApplication.fullstack.book;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceM {

    @Autowired
    private BooksRepo booksRepo;

    public JsonNode getBook(){
        return booksRepo.getList();
    }
}
