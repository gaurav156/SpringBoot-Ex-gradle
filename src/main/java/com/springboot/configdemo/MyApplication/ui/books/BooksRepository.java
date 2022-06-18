package com.springboot.configdemo.MyApplication.ui.books;

import com.springboot.configdemo.MyApplication.BookController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class BooksRepository {

    private final List<Books> booksList = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(BookController.class);

    public BooksRepository()
    {
        booksList.add( new Books("1","Harry Potter and Dungeon","12112", new String[]{"1", "2"}));
        booksList.add( new Books("2","Harry Potter and Dumbledore","23232", new String[]{"2"}));
        logger.info("Book Repository Initialised");
    }

    public List<Books> getList()
    {
        logger.debug("Book Repository - getList method call");
        return Collections.unmodifiableList(booksList);
    }

}
