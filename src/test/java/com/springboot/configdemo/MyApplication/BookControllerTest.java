package com.springboot.configdemo.MyApplication;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

//    @LocalServerPort
    @Value("${local.server.port}")
    private int port;

    @Test
    void readAllBooksTest(){
        Response response = RestAssured.get(createURLWithPort("/book"));
        System.out.println(response.getBody().asString());
        String expected = "[{\"bookCategory\":\"Science\",\"bookTitle\":\"Cosmos\",\"bookAuthor\":\"Rushikesh\"},{\"bookCategory\":\"Science\",\"bookTitle\":\"The Origin of Species\",\"bookAuthor\":\"Rushikesh\"},{\"bookCategory\":\"Engineering\",\"bookTitle\":\"The Design of Everyday Things\",\"bookAuthor\":\"Gaurav\"},{\"bookCategory\":\"Engineering\",\"bookTitle\":\"Engineer to Win\",\"bookAuthor\":\"Ganesh\"},{\"bookCategory\":\"Maths\",\"bookTitle\":\"Vedic Mathematics\",\"bookAuthor\":\"Gaurav\"},{\"bookCategory\":\"Maths\",\"bookTitle\":\"Trigonometry\",\"bookAuthor\":\"Rushikesh\"},{\"bookCategory\":\"Technology\",\"bookTitle\":\"AI Superpowers\",\"bookAuthor\":\"Gaurav\"}]";
        assertEquals(expected, response.getBody().asString(), "readAllBooks returns All the Books in JSON format when /books uri is fired");
    }

    @Test
    void readBookTest(){
        Response response = RestAssured.get(createURLWithPort("/book/category/Science"));
        System.out.println("JSON response : "+response.getBody().asString());

        String expected = "[{\"bookCategory\":\"Science\",\"bookTitle\":\"Cosmos\",\"bookAuthor\":\"Rushikesh\"},{\"bookCategory\":\"Science\",\"bookTitle\":\"The Origin of Species\",\"bookAuthor\":\"Rushikesh\"}]";

        assertEquals(expected, response.getBody().asString(), "readBook returns all the Books in a specific category in JSON format when /books/category/{category-name} uri is fired");
    }

    @Test
    void readBookByAuthorTest(){
        Response response = RestAssured.get(createURLWithPort("/book/author/Gaurav"));
        System.out.println("JSON response : "+response.getBody().asString());

        String expected = "[{\"bookCategory\":\"Engineering\",\"bookTitle\":\"The Design of Everyday Things\",\"bookAuthor\":\"Gaurav\"},{\"bookCategory\":\"Maths\",\"bookTitle\":\"Vedic Mathematics\",\"bookAuthor\":\"Gaurav\"},{\"bookCategory\":\"Technology\",\"bookTitle\":\"AI Superpowers\",\"bookAuthor\":\"Gaurav\"}]";

        assertEquals(expected, response.getBody().asString(), "readBookByAuthor returns all the Books by a specific Author in JSON format when /books/author/{author-name} uri is fired");
    }

    @Test
    void readBookInJSONTest(){
        Response response = RestAssured.get(createURLWithPort("/book/json"));
        System.out.println("JSON response : "+response.getBody().asString());

        String expected = "[{\"bookCategory\":\"Science\",\"bookTitle\":\"Cosmos\",\"bookAuthor\":\"Rushikesh\"},{\"bookCategory\":\"Science\",\"bookTitle\":\"The Origin of Species\",\"bookAuthor\":\"Rushikesh\"},{\"bookCategory\":\"Engineering\",\"bookTitle\":\"The Design of Everyday Things\",\"bookAuthor\":\"Gaurav\"},{\"bookCategory\":\"Engineering\",\"bookTitle\":\"Engineer to Win\",\"bookAuthor\":\"Ganesh\"},{\"bookCategory\":\"Maths\",\"bookTitle\":\"Vedic Mathematics\",\"bookAuthor\":\"Gaurav\"},{\"bookCategory\":\"Maths\",\"bookTitle\":\"Trigonometry\",\"bookAuthor\":\"Rushikesh\"},{\"bookCategory\":\"Technology\",\"bookTitle\":\"AI Superpowers\",\"bookAuthor\":\"Gaurav\"}]";

        assertEquals(expected, response.getBody().asString(),"readBookInJSON returns All the Books in JSON format when /books/json uri is fired");
    }

    @Test
    void readBookInXmlTest(){
        Response response = RestAssured.get(createURLWithPort("/book/xml"));
        System.out.println("XML response : "+response.getBody().asString());
//        String expected = "<List><item><bookCategory>Science</bookCategory><bookTitle>Cosmos</bookTitle><bookAuthor>Rushikesh</bookAuthor></item><item><bookCategory>Science</bookCategory><bookTitle>The Origin of Species</bookTitle><bookAuthor>Rushikesh</bookAuthor></item><item><bookCategory>Engineering</bookCategory><bookTitle>The Design of Everyday Things</bookTitle><bookAuthor>Gaurav</bookAuthor></item><item><bookCategory>Engineering</bookCategory><bookTitle>Engineer to Win</bookTitle><bookAuthor>Ganesh</bookAuthor></item><item><bookCategory>Maths</bookCategory><bookTitle>Vedic Mathematics</bookTitle><bookAuthor>Gaurav</bookAuthor></item><item><bookCategory>Maths</bookCategory><bookTitle>Trigonometry</bookTitle><bookAuthor>Rushikesh</bookAuthor></item><item><bookCategory>Technology</bookCategory><bookTitle>AI Superpowers</bookTitle><bookAuthor>Gaurav</bookAuthor></item></List>";
        String expected = "<List>" +
                "<item>" +
                "<bookCategory>Science</bookCategory>" +
                "<bookTitle>Cosmos</bookTitle>" +
                "<bookAuthor>Rushikesh</bookAuthor>" +
                "</item>" +
                "<item>" +
                "<bookCategory>Science</bookCategory>" +
                "<bookTitle>The Origin of Species</bookTitle>" +
                "<bookAuthor>Rushikesh</bookAuthor>" +
                "</item>" +
                "<item>" +
                "<bookCategory>Engineering</bookCategory>" +
                "<bookTitle>The Design of Everyday Things</bookTitle>" +
                "<bookAuthor>Gaurav</bookAuthor>" +
                "</item>" +
                "<item>" +
                "<bookCategory>Engineering</bookCategory>" +
                "<bookTitle>Engineer to Win</bookTitle>" +
                "<bookAuthor>Ganesh</bookAuthor>" +
                "</item>" +
                "<item>" +
                "<bookCategory>Maths</bookCategory>" +
                "<bookTitle>Vedic Mathematics</bookTitle>" +
                "<bookAuthor>Gaurav</bookAuthor>" +
                "</item>" +
                "<item>" +
                "<bookCategory>Maths</bookCategory>" +
                "<bookTitle>Trigonometry</bookTitle>" +
                "<bookAuthor>Rushikesh</bookAuthor>" +
                "</item>" +
                "<item>" +
                "<bookCategory>Technology</bookCategory>" +
                "<bookTitle>AI Superpowers</bookTitle>" +
                "<bookAuthor>Gaurav</bookAuthor>" +
                "</item>" +
                "</List>";

        assertEquals(expected, response.getBody().asString(), "readBookInXml returns All the Books in XML format when /books/xml uri is fired");
    }

    @Test
    void readAllBooksTest_2() {
        RestAssured
                .given()
                .get(createURLWithPort("/book"))
                .then()
                .statusCode(200)
                .body("bookCategory[0]", equalTo("Science"))
                .body("bookAuthor[3]", equalTo("Ganesh"))
                .body("bookCategory[6]", equalTo("Technology"))
                .and()
                .body("bookCategory", hasSize(7))
                .log().all();
    }

    @Test
    void readBookInJsonTest_2() {
        RestAssured
                .given()
                .get(createURLWithPort("/book/json"))
                .then()
                .statusCode(200)
                .body("bookCategory", hasItems("Science","Maths", "Technology", "Engineering"))
                .and()
                .body("bookCategory", hasSize(7))
                .log().all();
    }

    @Test
    void readBookByAuthorTest_2() {
        RestAssured
                .given()
                .get(createURLWithPort("/book/author/Gaurav"))
                .then()
                .statusCode(200)
                .body("bookAuthor", hasItem("Gaurav"))
                .and()
                .body("bookAuthor[0]", equalTo("Gaurav"),"bookAuthor", hasSize(3))
                .log().all();
    }

    @Test
    void readBookTest_2() {
        RestAssured
                .given()
                .get(createURLWithPort("/book/category/Science"))
                .then()
                .statusCode(200)
                .body("bookCategory", hasItem("Science"))
                .and()
                .body("bookCategory[0]", equalTo("Science"), "bookCategory", hasSize(2))
                .log().all();
    }

    @Test
    void readBookInXmlTest_2() {
        RestAssured
                .given()
                .accept(ContentType.XML)
                .when()
                .get(createURLWithPort("/book/xml"))
                .then()
                .assertThat()
                .statusCode(200)
                .body("List.item[0].bookCategory.text()", equalTo("Science"),
                        "List.item[1].bookCategory.text()", equalTo("Science"),
                        "List.item[2].bookCategory.text()", equalTo("Engineering"),
                        "List.item[3].bookCategory.text()", equalTo("Engineering"),
                        "List.item[4].bookCategory.text()", equalTo("Maths"),
                        "List.item[5].bookCategory.text()", equalTo("Maths"),
                        "List.item[6].bookCategory.text()", equalTo("Technology")
                        )
                .log().all();
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
