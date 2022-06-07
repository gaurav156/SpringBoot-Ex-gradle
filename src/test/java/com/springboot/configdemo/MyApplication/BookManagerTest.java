package com.springboot.configdemo.MyApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookManagerTest {

    @Autowired
    BookManager bookManager;

    @Test
    void getList() {

        List<Book> actualList = bookManager.getList();

        List<Book> expectedList = new ArrayList<>();
        expectedList.add( new Book("Science", "Cosmos", "Rushikesh"));
        expectedList.add( new Book("Science", "The Origin of Species", "Rushikesh"));
        expectedList.add( new Book("Engineering", "The Design of Everyday Things", "Gaurav"));
        expectedList.add( new Book("Engineering", "Engineer to Win", "Ganesh"));
        expectedList.add( new Book("Maths", "Vedic Mathematics", "Gaurav"));
        expectedList.add( new Book("Maths", "Trigonometry", "Rushikesh"));
        expectedList.add( new Book("Technology", "AI Superpowers", "Gaurav"));

        assertEquals(expectedList, actualList, "getList method returns the Book List");
    }

    @Test
    void categoryFilter() {

        List<Book> actualList = bookManager.categoryFilter("Science");

        List<Book> expectedList = new ArrayList<>();
        expectedList.add( new Book("Science", "Cosmos", "Rushikesh"));
        expectedList.add( new Book("Science", "The Origin of Species", "Rushikesh"));

        assertEquals(expectedList, actualList, "categoryFilter method returns the Books in a specific Category");
    }

    @Test
    void authorFilter() {

        List<Book> actualList = bookManager.authorFilter("Gaurav");

        List<Book> expectedList = new ArrayList<>();
        expectedList.add( new Book("Engineering", "The Design of Everyday Things", "Gaurav"));
        expectedList.add( new Book("Maths", "Vedic Mathematics", "Gaurav"));
        expectedList.add( new Book("Technology", "AI Superpowers", "Gaurav"));

        assertEquals(expectedList, actualList, "authorFilter method returns the Books of a specific Author");
    }

    @Test
    void categoryWiseCount() {

        Map<String, Long> actualList = bookManager.categoryWiseCount();

        Map<String, Long> expectedList = new HashMap<>();
        expectedList.put("Science", 2L);
        expectedList.put("Engineering", 2L);
        expectedList.put("Maths", 2L);
        expectedList.put("Technology", 1L);

        assertEquals(expectedList, actualList, "categoryWiseCount method returns the count of Books in categories");
    }

    @Test
    void categoryCount() {

        Map<String, Long> actualList = bookManager.categoryCount("Science");

        Map<String, Long> expectedList = new HashMap<>();
        expectedList.put("Science", 2L);

        assertEquals(expectedList, actualList, "categoryCount method returns the count of Books in a specific Category");
    }
}