package com.springboot.configdemo.MyApplication.fullstack.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;

public class Customer {

    private String customerID;
    private String customerName;
    private String membershipDate;
    private String[] bookID;

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

    public String[] getBookID() {
        logger.debug("getBookID method call");
        return bookID;
    }

    public void setBookID(String[] bookID) {
        logger.info("setBookID method call");
        this.bookID = bookID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.springboot.configdemo.MyApplication.fullstack.customer.Customer customer = (com.springboot.configdemo.MyApplication.fullstack.customer.Customer) o;
        return getCustomerID().equals(customer.getCustomerID()) && Objects.equals(getCustomerName(), customer.getCustomerName()) && Objects.equals(getMembershipDate(), customer.getMembershipDate()) && Arrays.equals(getBookID(), customer.getBookID());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getCustomerID(), getCustomerName(), getMembershipDate());
        result = 31 * result + Arrays.hashCode(getBookID());
        return result;
    }

    @Override
    public String toString() {
        return "customerID : " + customerID +
                "customerName : " + customerName +
                "membershipDate : " + membershipDate +
                "bookID : " + Arrays.toString(bookID) ;
    }
}

