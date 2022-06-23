package com.springboot.configdemo.MyApplication.ui.books;

import com.springboot.configdemo.MyApplication.BookControllerJ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {
    @Autowired
    private BooksRepository booksRepository;

    Logger logger = LoggerFactory.getLogger(BookControllerJ.class);

    public List<Books> getList()
    {
        logger.debug("BookJ Service - getList method call");
        return booksRepository.getList();
    }
}
