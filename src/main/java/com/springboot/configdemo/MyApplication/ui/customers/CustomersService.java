package com.springboot.configdemo.MyApplication.ui.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepository customersRepository;

    public List<Customers> getList()
    {
        return customersRepository.getList();
    }
}
