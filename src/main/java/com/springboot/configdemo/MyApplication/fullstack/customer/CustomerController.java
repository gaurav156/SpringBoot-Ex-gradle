package com.springboot.configdemo.MyApplication.fullstack.customer;

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
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Get Customers", description = "Get a list of All Customers", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Customers",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerService.class))}),
            @ApiResponse(responseCode = "404", description = "Customers not found",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.GET, value = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> getAllCustomers() throws IOException {
        LoggerFactory.getLogger(getClass()).info("GET request for list of all Customers from %s");
        return customerService.getCustomerList();
    }

    @Operation(summary = "Get a Customer", description = "Get a specific Customer detail by passing CustomerID", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Customer",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerService.class))}),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.GET, value = "/customers/{customerID}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> customerFilter(@PathVariable("customerID") String customerID) throws IOException {
        LoggerFactory.getLogger(getClass()).info(String.format("GET request for customerID : %s", customerID));
        return customerService.customerFilter(customerID);
    }

    @Operation(summary = "Add Customer", description = "Add a Customer", tags = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer Added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerService.class))}),
            @ApiResponse(responseCode = "404", description = "Failed",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.POST, value = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Customer addCustomer(@RequestBody Customer customer) {
        LoggerFactory.getLogger(getClass()).info("PUT request to Add Customer");
        return customerService.addCustomer(customer);
    }

    @Operation(summary = "Delete Customer", description = "Delete a Customer", tags = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer Deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerService.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content)
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/customer/{customerID}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("customerID") String customerID){
        LoggerFactory.getLogger(getClass()).info("DELETE request to delete a Customer");
        return customerService.deleteCustomer(customerID);
    }
}
