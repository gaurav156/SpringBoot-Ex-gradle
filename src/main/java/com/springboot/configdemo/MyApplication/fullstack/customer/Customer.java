package com.springboot.configdemo.MyApplication.fullstack.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;

public class Customer {
    private String customerID;
    private String customerName;
    private String membershipDate;
    private LinkedHashSet<String> bookID;

    Logger logger = LoggerFactory.getLogger(getClass());

    public String getCustomerID() {
        logger.debug("getCustomerID method call");
        return customerID;
    }

    public void setCustomerID(String customerID) {

        logger.info("setCustomerID method call");
        this.customerID = customerID;
    }

    public String getCustomerName() {
        logger.debug("getCustomerName method call");
        return customerName;
    }

    public void setCustomerName(String customerName) {
        logger.info("setCustomerName method call");
        this.customerName = customerName;
    }

    public String getMembershipDate() {
        logger.debug("getMembershipDate method call");
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        logger.info("setMembershipDate method call");
        this.membershipDate = membershipDate;
    }

    public LinkedHashSet<String> getBookID() {
        return bookID;
    }

    public void setBookID(LinkedHashSet<String> bookID) {
        this.bookID = bookID;
    }

    public void deleteBookID(String bookID){
        this.bookID.remove(bookID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getCustomerID().equals(customer.getCustomerID()) && getCustomerName().equals(customer.getCustomerName()) && Objects.equals(getMembershipDate(), customer.getMembershipDate()) && Objects.equals(getBookID(), customer.getBookID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerID(), getCustomerName(), getMembershipDate(), getBookID());
    }

    @Override
    public String toString() {
        return "customerID : " + customerID +
                "customerName : " + customerName +
                "membershipDate : " + membershipDate +
                "bookID : " + bookID ;
    }
}

