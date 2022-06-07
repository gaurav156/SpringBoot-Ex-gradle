package com.springboot.configdemo.MyApplication;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTestUsingRestTemplate {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    void readAllBooks() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/book"),
                HttpMethod.GET, entity, String.class);

        String expected = "[{\"bookCategory\":\"Science\",\"bookTitle\":\"Cosmos\",\"bookAuthor\":\"Rushikesh\"},{\"bookCategory\":\"Science\",\"bookTitle\":\"The Origin of Species\",\"bookAuthor\":\"Rushikesh\"},{\"bookCategory\":\"Engineering\",\"bookTitle\":\"The Design of Everyday Things\",\"bookAuthor\":\"Gaurav\"},{\"bookCategory\":\"Engineering\",\"bookTitle\":\"Engineer to Win\",\"bookAuthor\":\"Ganesh\"},{\"bookCategory\":\"Maths\",\"bookTitle\":\"Vedic Mathematics\",\"bookAuthor\":\"Gaurav\"},{\"bookCategory\":\"Maths\",\"bookTitle\":\"Trigonometry\",\"bookAuthor\":\"Rushikesh\"},{\"bookCategory\":\"Technology\",\"bookTitle\":\"AI Superpowers\",\"bookAuthor\":\"Gaurav\"}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void readBook() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/book/category/Science"),
                HttpMethod.GET, entity, String.class);

        String expected = "[{\"bookCategory\":\"Science\",\"bookTitle\":\"Cosmos\",\"bookAuthor\":\"Rushikesh\"},{\"bookCategory\":\"Science\",\"bookTitle\":\"The Origin of Species\",\"bookAuthor\":\"Rushikesh\"}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void readBookByAuthor() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/book/author/Gaurav"),
                HttpMethod.GET, entity, String.class);

        String expected = "[{\"bookCategory\":\"Engineering\",\"bookTitle\":\"The Design of Everyday Things\",\"bookAuthor\":\"Gaurav\"},{\"bookCategory\":\"Maths\",\"bookTitle\":\"Vedic Mathematics\",\"bookAuthor\":\"Gaurav\"},{\"bookCategory\":\"Technology\",\"bookTitle\":\"AI Superpowers\",\"bookAuthor\":\"Gaurav\"}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void readBookInJSON() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/book/json"),
                HttpMethod.GET, entity, String.class);

        String expected = "[{\"bookCategory\":\"Science\",\"bookTitle\":\"Cosmos\",\"bookAuthor\":\"Rushikesh\"},{\"bookCategory\":\"Science\",\"bookTitle\":\"The Origin of Species\",\"bookAuthor\":\"Rushikesh\"},{\"bookCategory\":\"Engineering\",\"bookTitle\":\"The Design of Everyday Things\",\"bookAuthor\":\"Gaurav\"},{\"bookCategory\":\"Engineering\",\"bookTitle\":\"Engineer to Win\",\"bookAuthor\":\"Ganesh\"},{\"bookCategory\":\"Maths\",\"bookTitle\":\"Vedic Mathematics\",\"bookAuthor\":\"Gaurav\"},{\"bookCategory\":\"Maths\",\"bookTitle\":\"Trigonometry\",\"bookAuthor\":\"Rushikesh\"},{\"bookCategory\":\"Technology\",\"bookTitle\":\"AI Superpowers\",\"bookAuthor\":\"Gaurav\"}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}