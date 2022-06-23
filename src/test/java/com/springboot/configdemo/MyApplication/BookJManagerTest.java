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
class BookJManagerTest {

    @Autowired
    BookManager bookManager;

    @Test
    void getList() {

        List<BookJ> actualList = bookManager.getList();

        List<BookJ> expectedList = new ArrayList<>();
        expectedList.add( new BookJ("Science", "Cosmos", "Rushikesh"));
        expectedList.add( new BookJ("Science", "The Origin of Species", "Rushikesh"));
        expectedList.add( new BookJ("Engineering", "The Design of Everyday Things", "Gaurav"));
        expectedList.add( new BookJ("Engineering", "Engineer to Win", "Ganesh"));
        expectedList.add( new BookJ("Maths", "Vedic Mathematics", "Gaurav"));
        expectedList.add( new BookJ("Maths", "Trigonometry", "Rushikesh"));
        expectedList.add( new BookJ("Technology", "AI Superpowers", "Gaurav"));

        assertEquals(expectedList, actualList, "getList method returns the BookJ List");
    }

    @Test
    void categoryFilter() {

        List<BookJ> actualList = bookManager.categoryFilter("Science");

        List<BookJ> expectedList = new ArrayList<>();
        expectedList.add( new BookJ("Science", "Cosmos", "Rushikesh"));
        expectedList.add( new BookJ("Science", "The Origin of Species", "Rushikesh"));

        assertEquals(expectedList, actualList, "categoryFilter method returns the Books in a specific Category");
    }

    @Test
    void authorFilter() {

        List<BookJ> actualList = bookManager.authorFilter("Gaurav");

        List<BookJ> expectedList = new ArrayList<>();
        expectedList.add( new BookJ("Engineering", "The Design of Everyday Things", "Gaurav"));
        expectedList.add( new BookJ("Maths", "Vedic Mathematics", "Gaurav"));
        expectedList.add( new BookJ("Technology", "AI Superpowers", "Gaurav"));

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