package com.springboot.configdemo.MyApplication.fullstack.book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/marklogic")
public class bookController {

    @Autowired
    private BookService bookService;

    @Operation(summary = "Get Books", description = "Get a list of All Books", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Books",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = BookService.class))}),
            @ApiResponse(responseCode = "404", description = "Books not found",
            content = @Content)
    })
    @RequestMapping(method = RequestMethod.GET, value = "/books", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Book> readAllBooks() {
        LoggerFactory.getLogger(getClass()).info("GET request for list of all Books");
        return bookService.getBookList();
    }

    @Operation(summary = "Get a Book", description = "Get a specific Book detail by passing BookID", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookService.class))}),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.GET, value = "/books/{bookID}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Book> bookFilter(@PathVariable("bookID") String bookID){
        LoggerFactory.getLogger(getClass()).info(String.format("GET request for bookID : %s", bookID));
        return bookService.bookFilter(bookID);
    }
}


