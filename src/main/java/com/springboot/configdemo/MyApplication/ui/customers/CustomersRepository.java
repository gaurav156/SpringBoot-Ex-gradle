package com.springboot.configdemo.MyApplication.ui.customers;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class CustomersRepository {

    private final List<Customers> customersList = new ArrayList<>();

    public CustomersRepository()
    {
        customersList.add( new Customers("1","Somnath","01-Dec-2020", new String[]{"1"}));
        customersList.add( new Customers("2","Pravin","01-Dec-2022", new String[]{"1", "2"}));
    }

    public List<Customers> getList()
    {
        return Collections.unmodifiableList(customersList);
    }

}
