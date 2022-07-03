package com.springboot.configdemo.MyApplication.fullstack.book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public List<Book> readAllBooks() throws IOException {
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
    public List<Book> bookFilter(@PathVariable("bookID") String bookID) throws IOException {
        LoggerFactory.getLogger(getClass()).info(String.format("GET request for bookID : %s", bookID));
        return bookService.bookFilter(bookID);
    }

    @Operation(summary = "Add Book", description = "Add a Books", tags = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book Added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookService.class))}),
            @ApiResponse(responseCode = "404", description = "Failed",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.POST, value = "/books", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Book addBook(@RequestBody Book book) {
        LoggerFactory.getLogger(getClass()).info("PUT request to Add Book");
        return bookService.addBook(book);
    }

    @Operation(summary = "Delete Book", description = "Delete a Book", tags = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book Deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookService.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/books/{bookID}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("bookID") String bookID){
        LoggerFactory.getLogger(getClass()).info("DELETE request to delete a Book");
        return bookService.deleteBook(bookID);
    }
}


