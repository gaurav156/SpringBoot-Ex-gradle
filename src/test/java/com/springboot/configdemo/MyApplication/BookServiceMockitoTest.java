package com.springboot.configdemo.MyApplication;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceMockitoTest {

    @InjectMocks
    private BookManager bookManager;

    @Mock
    private BookRepository bookRepository;

    @Test
    void getListMock(){
        List<Book> mockList = new ArrayList<>();
        mockList.add(new Book("Spring", "Unit Testing in Spring Boot", "Gaurav"));
        mockList.add(new Book("Vue Js", "Vue Js Framework", "Ganesh"));

        when(bookRepository.getList()).thenReturn(mockList);

        List<Book> returnedList = bookManager.getList();

        assertEquals(2, returnedList.size());
        verify(bookRepository, times(1)).getList();
        assertEquals(mockList, returnedList, "getList method returns the Book List");

    }

    @Test
    void categoryFilterMock(){
        List<Book> mockList = new ArrayList<>();
        mockList.add(new Book("Science", "Unit Testing in Spring Boot", "Gaurav"));

        String category = "Science";

        when(bookRepository.categoryFilter(category)).thenReturn(mockList);

        List<Book> returnedList = bookManager.categoryFilter(category);

        assertEquals(1, returnedList.size());
        verify(bookRepository, times(1)).categoryFilter(category);
        assertEquals(mockList, returnedList, "categoryFilter method returns the Books in a specific Category");
    }


    @Test
    void authorFilterMock(){
        List<Book> mockList = new ArrayList<>();
        mockList.add(new Book("History", "Unit Testing in Spring Boot", "Gaurav"));

        String author = "Gaurav";
        when(bookRepository.authorFilter(author)).thenReturn(mockList);

        List<Book> returnedList = bookManager.authorFilter(author);

        assertEquals(1, returnedList.size());
        verify(bookRepository, times(1)).authorFilter(author);
        assertEquals(mockList, returnedList, "authorFilter method returns the Books of a specific Author");
    }

    @Test
    void categoryWiseCountMock(){

        Map<String, Long> mockList = new HashMap<>();
        mockList.put("Science", 2L);
        mockList.put("Engineering", 2L);
        mockList.put("Maths", 2L);
        mockList.put("Technology", 1L);
        mockList.put("Spring", 1L);
        when(bookRepository.categoryWiseCount()).thenReturn(mockList);

        Map<String, Long> returnedList = bookManager.categoryWiseCount();

        assertEquals(5, returnedList.size());
        verify(bookRepository, times(1)).categoryWiseCount();
        assertEquals(mockList, returnedList, "categoryWiseCount method returns the count of Books in categories");

    }


    @Test
    void categoryCountMock() {
        Map<String, Long> mockList = new HashMap<>();
        mockList.put("Spring", 1L);

        String category = "Spring";
        when(bookRepository.categoryCount(category)).thenReturn(mockList);

        Map<String, Long> returnedList = bookManager.categoryCount(category);

        assertEquals(1, returnedList.size());
        verify(bookRepository, times(1)).categoryCount(category);
        assertEquals(mockList, returnedList, "categoryCount method returns the count of Books in a specific Category");

    }

}
