package com.springboot.configdemo.MyApplication.ui.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomersController {
    @Autowired
    private CustomersService customersService;

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(method = RequestMethod.GET, value = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customers> readAllBooks() {
        return customersService.getList();
    }

}
