package com.springboot.configdemo.MyApplication.ui.customers;

import com.springboot.configdemo.MyApplication.BookControllerJ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class CustomersRepository {

    private final List<Customers> customersList = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(BookControllerJ.class);

    public CustomersRepository()
    {
        customersList.add( new Customers("1","Somnath","01-Dec-2020", new String[]{"1"}));
        customersList.add( new Customers("2","Pravin","01-Dec-2022", new String[]{"1", "2"}));
        logger.info("Customers Repository Initialised");
    }

    public List<Customers> getList()
    {
        logger.debug("Customers Repository - getList method call");
        return Collections.unmodifiableList(customersList);
    }

}
