package com.springboot.configdemo.MyApplication.fullstack.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;

public class Book {
    private String bookID;
    private String bookTitle;
    private String issnNo;
    private String[] customerID;
    Logger logger = LoggerFactory.getLogger(getClass());

//    public Book(String bookID, String bookTitle, String issnNo, String[] customerID) {
//        this.bookID = bookID;
//        this.bookTitle = bookTitle;
//        this.issnNo = issnNo;
//        this.customerID = customerID;
//    }

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

    public String[] getCustomerID() {
        logger.debug("getCustomerID method call");
        return customerID;
    }

    public void setCustomerID(String[] customerID) {
        logger.debug("setCustomerID method call");
        this.customerID = customerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.springboot.configdemo.MyApplication.fullstack.book.Book book = (com.springboot.configdemo.MyApplication.fullstack.book.Book) o;
        return getBookID().equals(book.getBookID()) && Objects.equals(getBookTitle(), book.getBookTitle()) && Objects.equals(getIssnNo(), book.getIssnNo()) && Arrays.equals(getCustomerID(), book.getCustomerID());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getBookID(), getBookTitle(), getIssnNo());
        result = 31 * result + Arrays.hashCode(getCustomerID());
        return result;
    }

    @Override
    public String toString() {
        return "BookID : " + bookID +
                "bookTitle : " + bookTitle +
                "bookIssnNo : " + issnNo +
                "bookAuthor : " + Arrays.toString(customerID) ;
    }
}
