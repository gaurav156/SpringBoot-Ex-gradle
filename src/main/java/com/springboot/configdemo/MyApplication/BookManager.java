package com.springboot.configdemo.MyApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookManager {

    @Autowired
    BookRepository bookRepository;

    public void printBooks(List<Book> list){
        list.forEach(x -> System.out.println("\t"+x));
    }
    public void printCount(Map<String, Long> countList){
        countList.forEach((category, count) -> System.out.println("\t"+category+" : "+count));
    }

    //		Create a static method that returns all the books as a list collection
    public List<Book> getList()
    {
        return bookRepository.getList();
    }


    //		Find all the books based on category filter value  e.g Science, Maths, Engineering, technology

    public List<Book> categoryFilter(String category){
        return bookRepository.categoryFilter(category);
    }


    //		Find all the books based on Author filter value e.g Rushikesh, Ganesh, Gaurav

    public List<Book> authorFilter(String author){
        return bookRepository.authorFilter(author);
    }


    //		Find count of all the books based on Category (hint using lambda and grouping)

    public Map<String, Long> categoryWiseCount(){
        return bookRepository.categoryWiseCount();
    }

    public Map<String, Long> categoryCount(String category){
        return bookRepository.categoryCount(category);
    }

}
