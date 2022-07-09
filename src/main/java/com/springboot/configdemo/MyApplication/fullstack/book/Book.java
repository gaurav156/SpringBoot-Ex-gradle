package com.springboot.configdemo.MyApplication.fullstack.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashSet;
import java.util.Objects;

public class Book {
    private String bookID;
    private String bookTitle;
    private String issnNo;
    private LinkedHashSet<String> customerID;
    Logger logger = LoggerFactory.getLogger(getClass());

    public String getBookID() {
        logger.debug("getBookID method call");
        return bookID;
    }

    public void setBookID(String bookID) {
        logger.debug("setBookID method call");
        this.bookID = bookID;
    }

    public String getBookTitle() {
        logger.debug("getBookTitle method call");
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        logger.debug("setBookTitle method call");
        this.bookTitle = bookTitle;
    }

    public String getIssnNo() {
        logger.debug("getIssnNo method call");
        return issnNo;
    }

    public void setIssnNo(String issnNo) {
        logger.debug("setIssnNo method call");
        this.issnNo = issnNo;
    }

    public LinkedHashSet<String> getCustomerID() {
        logger.debug("getCustomerID method call");
        return customerID;
    }

    public void setCustomerID(LinkedHashSet<String> customerID) {
        logger.debug("setCustomerID method call");
        this.customerID = customerID;
    }

    public void deleteCustomerID(String customerID){
        logger.debug("deleteCustomerID method call");
        this.customerID.remove(customerID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getBookID().equals(book.getBookID()) && getBookTitle().equals(book.getBookTitle()) && Objects.equals(getIssnNo(), book.getIssnNo()) && Objects.equals(getCustomerID(), book.getCustomerID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookID(), getBookTitle(), getIssnNo(), getCustomerID());
    }

    @Override
    public String toString() {
        return "BookID : " + bookID +
                "bookTitle : " + bookTitle +
                "bookIssnNo : " + issnNo +
                "bookAuthor : " + customerID ;
    }
}
