package com.springboot.configdemo.MyApplication;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    private final List<BookJ> bookList = new ArrayList<>();

    public BookRepository()
    {
        bookList.add( new BookJ("Science", "Cosmos", "Rushikesh"));
        bookList.add( new BookJ("Science", "The Origin of Species", "Rushikesh"));
        bookList.add( new BookJ("Engineering", "The Design of Everyday Things", "Gaurav"));
        bookList.add( new BookJ("Engineering", "Engineer to Win", "Ganesh"));
        bookList.add( new BookJ("Maths", "Vedic Mathematics", "Gaurav"));
        bookList.add( new BookJ("Maths", "Trigonometry", "Rushikesh"));
        bookList.add( new BookJ("Technology", "AI Superpowers", "Gaurav"));
    }


    //		returns all the books as a list collection
    public List<BookJ> getList()
    {
        return Collections.unmodifiableList(bookList);
    }

    //		Find all the books based on category filter value  e.g Science, Maths, Engineering, technology

    public List<BookJ> categoryFilter(String category){
        return getList().stream().filter(p -> p.getBookCategory().equals(category)).collect(Collectors.toList());
    }


    //		Find all the books based on Author filter value e.g Rushikesh, Ganesh, Gaurav

    public List<BookJ> authorFilter(String author){
        return getList().stream().filter(p -> p.getBookAuthor().equals(author)).collect(Collectors.toList());
    }


    //		Find count of all the books based on Category (hint using lambda and grouping)

    public Map<String, Long> categoryWiseCount(){
        return getList().stream().map(p -> p.getBookCategory()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Map<String, Long> categoryCount(String category){
        return getList().stream().map(p -> p.getBookCategory()).filter(p -> Objects.equals(p, category)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

}
