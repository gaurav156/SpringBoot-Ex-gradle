package com.springboot.configdemo.MyApplication.fullstack.user;

import com.springboot.configdemo.MyApplication.fullstack.book.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/marklogic")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get Users", description = "Get a list of All Users", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Users",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserService.class))}),
            @ApiResponse(responseCode = "404", description = "Users not found",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.GET, value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> readAllUsers() throws IOException {
        return userService.getUserList();
    }

    @Operation(summary = "Get a User", description = "Get a specific User detail by passing id", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserService.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> userFilter(@PathVariable("id") String id) throws IOException {
        return userService.userFilter(id);
    }

    @Operation(summary = "Add User", description = "Add a User", tags = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserService.class))}),
            @ApiResponse(responseCode = "404", description = "Failed",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.POST, value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User addUser(@RequestBody User user) throws IOException {
        return userService.addUser(user);
    }

    @Operation(summary = "Authenticate a User", description = "Authenticate a specific User detail by passing email & password", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Authenticated Successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserService.class))}),
            @ApiResponse(responseCode = "404", description = "User Authentication Failed",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.GET, value = "/users/{email}/{password}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User authenticateUser(@PathVariable("email") String email, @PathVariable("password") String password) throws IOException {
        return userService.authenticateUser(email, password);
    }
}
