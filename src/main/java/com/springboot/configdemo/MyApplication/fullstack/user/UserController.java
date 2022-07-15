package com.springboot.configdemo.MyApplication.fullstack.user;

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
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get Users", description = "Get a list of All Users", tags = {"Get", "User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Users",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserService.class))}),
            @ApiResponse(responseCode = "404", description = "Users not found",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.GET, value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> readAllUsers() throws IOException {
        LoggerFactory.getLogger(getClass()).info("GET request for list of all Users");
        return userService.getUserList();
    }

    @Operation(summary = "Get a User", description = "Get a specific User detail by passing id", tags = {"Get", "User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserService.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User userFilter(@PathVariable("id") String id) throws IOException {
        LoggerFactory.getLogger(getClass()).info(String.format("GET request for userID : %s", id));
        return userService.userFilter(id);
    }

    @Operation(summary = "Add User", description = "Add a User", tags = {"Post", "User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserService.class))}),
            @ApiResponse(responseCode = "404", description = "Failed",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.POST, value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User addUser(@RequestBody User user) throws IOException {
        LoggerFactory.getLogger(getClass()).info("POST request to Add User");
        return userService.addUser(user);
    }

    @Operation(summary = "Authenticate a User", description = "Authenticate a specific User detail by passing email & password", tags = {"Get", "User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Authenticated Successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserService.class))}),
            @ApiResponse(responseCode = "404", description = "User Authentication Failed",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.GET, value = "/users/{email}/{password}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User authenticateUser(@PathVariable("email") String email, @PathVariable("password") String password) throws IOException {
        LoggerFactory.getLogger(getClass()).info("GET request to Authenticate User");
        return userService.authenticateUser(email, password);
    }

    @Operation(summary = "Reset Password", description = "Reset password of specific by passing email & new password", tags = {"Post", "User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password reset Successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserService.class))}),
            @ApiResponse(responseCode = "404", description = "User doesn't exists",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.POST, value = "/reset/{email}/{password}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> resetPassword(@PathVariable("email") String email, @PathVariable("password") String password){
        return userService.resetPassword(email, password);
    }

    @Operation(summary = "Check Email", description = "Check is an Email is present or not", tags = {"Get", "User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email Present",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserService.class))}),
            @ApiResponse(responseCode = "404", description = "Email Not Present",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.GET, value = "/check/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean checkEmail(@PathVariable("email") String email) throws IOException {
        return userService.checkEmail(email);
    }
}
