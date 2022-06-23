package com.springboot.configdemo.MyApplication.ui.customers;

import com.springboot.configdemo.MyApplication.BookControllerJ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepository customersRepository;

    Logger logger = LoggerFactory.getLogger(BookControllerJ.class);

    public List<Customers> getList()
    {
        logger.debug("Customers Service - getList method call");
        return customersRepository.getList();
    }
}
