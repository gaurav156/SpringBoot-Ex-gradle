package com.springboot.configdemo.MyApplication;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    private final List<Book> bookList = new ArrayList<>();

    public BookRepository()
    {
        bookList.add( new Book("Science", "Cosmos", "Rushikesh"));
        bookList.add( new Book("Science", "The Origin of Species", "Rushikesh"));
        bookList.add( new Book("Engineering", "The Design of Everyday Things", "Gaurav"));
        bookList.add( new Book("Engineering", "Engineer to Win", "Ganesh"));
        bookList.add( new Book("Maths", "Vedic Mathematics", "Gaurav"));
        bookList.add( new Book("Maths", "Trigonometry", "Rushikesh"));
        bookList.add( new Book("Technology", "AI Superpowers", "Gaurav"));
    }


    //		returns all the books as a list collection
    public List<Book> getList()
    {
        return Collections.unmodifiableList(bookList);
    }

    //		Find all the books based on category filter value  e.g Science, Maths, Engineering, technology

    public List<Book> categoryFilter(String category){
        return getList().stream().filter(p -> p.getBookCategory().equals(category)).collect(Collectors.toList());
    }


    //		Find all the books based on Author filter value e.g Rushikesh, Ganesh, Gaurav

    public List<Book> authorFilter(String author){
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
