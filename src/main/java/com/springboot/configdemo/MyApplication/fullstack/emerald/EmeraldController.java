package com.springboot.configdemo.MyApplication.fullstack.emerald;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;

@RestController
public class EmeraldController {
    @Autowired
    private EmeraldRepo emeraldRepo;

//    @Operation(summary = "Get Books", description = "Get a list of All Books", tags = "Get")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Found the Books",
//                    content = {@Content(mediaType = "application/json",
//                            schema = @Schema(implementation = BookService.class))}),
//            @ApiResponse(responseCode = "404", description = "Books not found",
//                    content = @Content)
//    })
    @Operation(hidden = true)
    @RequestMapping(method = RequestMethod.GET, value = "/emerald/books", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<EmeraldBooks> readAllBooks() {
//        LoggerFactory.getLogger(getClass()).info("GET request for list of all Books");
        return emeraldRepo.getBookList();
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/emerald/book/{bookID}", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public void insertBooks(@PathVariable("bookID") String bookID, @RequestBody EmeraldBooks books) throws IOException {
    @Operation(hidden = true)
    @RequestMapping(method = RequestMethod.POST, value = "/emerald/books", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void insertBooks(@RequestBody EmeraldBooks books) throws IOException {
//        LoggerFactory.getLogger(getClass()).info("GET request for list of all Books");
        emeraldRepo.insertBook(books);
    }

//    @RequestMapping("/books/{bookId}")
//    public Book getBook(@PathVariable("bookId") String bookId){
//        return bookService.getBook(bookId);
//    }
//    //    @RequestMapping(method = RequestMethod.POST, path = "/books", consumes = "application/json")
//    @PostMapping("/books")
//    public String addBookController(@RequestBody Book book){
//        return "Hi";
//    }

    @Operation(hidden = true)
    @RequestMapping(method = RequestMethod.GET, value = "/emerald/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    public LinkedHashSet<EmeraldBooks> getList() throws IOException {
        return emeraldRepo.getList();
    }
}
