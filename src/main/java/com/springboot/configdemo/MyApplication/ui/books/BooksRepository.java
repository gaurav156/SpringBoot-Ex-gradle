package com.springboot.configdemo.MyApplication.ui.books;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class BooksRepository {

    private final List<Books> booksList = new ArrayList<>();

    public BooksRepository()
    {
        booksList.add( new Books("1","Harry Potter and Dungeon","12112", new String[]{"1", "2"}));
        booksList.add( new Books("2","Harry Potter and Dumbledore","23232", new String[]{"2"}));
    }

    public List<Books> getList()
    {
        return Collections.unmodifiableList(booksList);
    }

}
