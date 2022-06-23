package com.springboot.configdemo.MyApplication.ui.customers;

import com.springboot.configdemo.MyApplication.BookControllerJ;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(BookControllerJ.class);

    @Operation(hidden = true)
    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(method = RequestMethod.GET, value = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customers> readAllBooks() {
        logger.info("GET request for all Customers");
        return customersService.getList();
    }

}
